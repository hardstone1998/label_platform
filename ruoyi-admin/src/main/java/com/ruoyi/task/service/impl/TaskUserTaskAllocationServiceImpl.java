package com.ruoyi.task.service.impl;

import java.util.List;

import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.asr.mapper.VoiceAnnotationMapper;
import com.ruoyi.asr.service.IVoiceAnnotationService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.AddVerityUser;
import com.ruoyi.task.domain.TaskUserTaskAllocation;
import com.ruoyi.task.domain.VerityTaskAllocationReq;
import com.ruoyi.task.domain.VerityTaskSysUser;
import com.ruoyi.task.mapper.TaskUserTaskAllocationMapper;
import com.ruoyi.task.service.ITaskUserTaskAllocationService;
import com.ruoyi.task.service.IVerityTaskSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-01-10
 */
@Service
public class TaskUserTaskAllocationServiceImpl implements ITaskUserTaskAllocationService
{
    @Autowired
    private TaskUserTaskAllocationMapper taskUserTaskAllocationMapper;

    @Autowired
    private IVerityTaskSysUserService verityTaskSysUserService;

    @Autowired
    private IVoiceAnnotationService voiceAnnotationService;

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TaskUserTaskAllocation selectTaskUserTaskAllocationById(Long id)
    {
        return taskUserTaskAllocationMapper.selectTaskUserTaskAllocationById(id);
    }

    /**
     * 查询任务用户列表
     *
     * @param taskUserTaskAllocation 任务用户
     * @return 任务用户
     * todo
     */
    @Override
    public List<TaskUserTaskAllocation> selectTaskUserTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        List<TaskUserTaskAllocation> taskUserTaskAllocations = taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        System.out.println("taskUserTaskAllocations:::"+taskUserTaskAllocations);
        for (TaskUserTaskAllocation t:taskUserTaskAllocations) {
            VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
            verityTaskSysUser.setTaskId(t.getTaskId());
            verityTaskSysUser.setLabelUserId(t.getUserId());
            List<VerityTaskSysUser> verityTaskSysUsers = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUser);
            if(verityTaskSysUsers.size()>0){
                t.setVerityUser(verityTaskSysUsers.get(0).getVerityUserName());
            }
            VoiceAnnotation voiceAnnotation =new VoiceAnnotation();
            voiceAnnotation.setLabelUser(t.getUserId());
            voiceAnnotation.setTaskId(t.getTaskId());
            voiceAnnotation.setIsMark("是");
            int labelNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
            int verityNum = voiceAnnotationService.selectVoiceAnnotationVerityCount(voiceAnnotation);
            t.setLabelNum(String.valueOf(labelNum));
            t.setVerityNum(String.valueOf(verityNum));
            Double wordAccuracy = voiceAnnotationService.selectVoiceAnnotationWordAccuracy(voiceAnnotation);
            t.setWordAccuracy(wordAccuracy);
            voiceAnnotation.setIsPass(1);
            int passNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
            if (labelNum>0)
                t.setNumberAccuracy(1.0*passNum/labelNum);
            int recall = voiceAnnotationService.selectVoiceAnnotationRecall(voiceAnnotation);
            t.setRecallNum(recall);
        }
        return taskUserTaskAllocations;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTaskUserTaskAllocation(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        return taskUserTaskAllocationMapper.insertTaskUserTaskAllocation(taskUserTaskAllocation);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTaskUserTaskAllocation(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        return taskUserTaskAllocationMapper.updateTaskUserTaskAllocation(taskUserTaskAllocation);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTaskUserTaskAllocationByIds(Long[] ids)
    {
        return taskUserTaskAllocationMapper.deleteTaskUserTaskAllocationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTaskUserTaskAllocationById(Long id)
    {
        return taskUserTaskAllocationMapper.deleteTaskUserTaskAllocationById(id);
    }

    /**
     * 修改审核任务
     *
     * @param verityTaskAllocationReq
     * @return 结果
     */
    @Override
    @Transactional
    public int verityAllocation(VerityTaskAllocationReq verityTaskAllocationReq) {
        VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
        voiceAnnotation.setLabelUser(verityTaskAllocationReq.getLabelUserId());
        voiceAnnotation.setTaskId(verityTaskAllocationReq.getTaskId());
        int num = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
        Long verityNum = verityTaskAllocationReq.getVerityNum();
        if (verityNum==null || verityNum == 0){
            Double verityPercentage = verityTaskAllocationReq.getVerityPercentage();
            if (verityPercentage == null ||verityPercentage==0)throw new RuntimeException("审核数量为空");
            verityTaskAllocationReq.setVerityNum((long) (num*verityPercentage));
        }
        if (num<verityNum)throw new RuntimeException("分配审核数量大于实际标注数量");

        VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
        long id = verityTaskAllocationReq.getId();
        VerityTaskSysUser verityTaskSysUserSearch =new VerityTaskSysUser();
        verityTaskSysUserSearch.setTaskId(verityTaskAllocationReq.getTaskId());
        verityTaskSysUserSearch.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
        VerityTaskSysUser verityTaskSysUser1 = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUserSearch).get(0);
//        verityTaskSysUser.setId(id);
        verityTaskSysUser.setTaskId(verityTaskAllocationReq.getTaskId());
        verityTaskSysUser.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
        verityTaskSysUser.setVerityUserId(verityTaskAllocationReq.getVerityUserId());
//        verityTaskSysUser.setCreateTime(DateUtils.getNowDate());
//        verityTaskSysUser.setUpdateTime(DateUtils.getNowDate());
        String reqUser = verityTaskAllocationReq.getReqUser();
        SysUser sysUser = sysUserService.selectUserByUserName(reqUser);
        verityTaskSysUser.setReqUser(sysUser.getUserId());
        verityTaskSysUser.setExtractNum(verityNum);
        int i = verityTaskSysUserService.updateVerityTaskSysUserByTaskIdAndLabelUserId(verityTaskSysUser);

        if (verityTaskSysUser1.getVerityUserId() != verityTaskSysUser.getVerityUserId()){
            VoiceAnnotation v = new VoiceAnnotation();
            v.setVerityUser(verityTaskSysUser.getVerityUserId());
            v.setTaskId(verityTaskAllocationReq.getTaskId());
            v.setLabelUser(verityTaskAllocationReq.getLabelUserId());
            voiceAnnotationService.updateVoiceAnnotationByTaskAndLabelUser(v);
        }

        if (verityTaskAllocationReq.getVerityNum() >verityTaskSysUser1.getExtractNum()){
            AddVerityUser addVerityUser = new AddVerityUser();
            addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
            addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
            addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
            addVerityUser.setVerityNum(verityTaskAllocationReq.getVerityNum()-verityTaskSysUser1.getExtractNum());
            voiceAnnotationService.addVerity(addVerityUser);
        }else if (verityTaskAllocationReq.getVerityNum() <verityTaskSysUser1.getExtractNum()&&verityTaskAllocationReq.getVerityNum()>0){
            AddVerityUser addVerityUser = new AddVerityUser();
            addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
            addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
            addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
            addVerityUser.setVerityNum(verityTaskSysUser1.getExtractNum()-verityTaskAllocationReq.getVerityNum());
            voiceAnnotationService.subtractVerity(addVerityUser);
        }


        return i;
    }
}
