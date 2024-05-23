package com.ruoyi.total.task.service.impl;

import java.util.List;

import com.ruoyi.gugong.asr.domain.VoiceAnnotation;
import com.ruoyi.gugong.asr.service.IVoiceAnnotationService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.qa.domain.AsrResult1;
import com.ruoyi.gugong.qa.service.IAsrResult1Service;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.total.task.domain.AddVerityUser;
import com.ruoyi.total.task.domain.TaskUserTaskAllocation;
import com.ruoyi.total.task.domain.VerityTaskAllocationReq;
import com.ruoyi.total.task.domain.VerityTaskSysUser;
import com.ruoyi.total.task.mapper.TaskUserTaskAllocationMapper;
import com.ruoyi.total.task.service.ITaskUserTaskAllocationService;
import com.ruoyi.total.task.service.IVerityTaskSysUserService;
import com.ruoyi.total.tool.domain.LabelStatistics;
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
    private IAsrResult1Service asrResult1Service;

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
     */
    @Override
    public List<TaskUserTaskAllocation> selectTaskAllocationListByUserAndProject(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        List<TaskUserTaskAllocation> taskUserTaskAllocations = taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        return taskUserTaskAllocations;
    }

    /**
     * 查询任务用户列表
     *
     * @param taskUserTaskAllocation 任务用户
     * @return 任务用户
     */
    @Override
    public List<TaskUserTaskAllocation> selectTaskUserTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        List<TaskUserTaskAllocation> taskUserTaskAllocations = taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        for (TaskUserTaskAllocation t:taskUserTaskAllocations) {
            VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
            verityTaskSysUser.setTaskId(t.getTaskId());
            verityTaskSysUser.setLabelUserId(t.getUserId());
            List<VerityTaskSysUser> verityTaskSysUsers = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUser);
            if(verityTaskSysUsers.size()>0){
                SysUser sysUser = sysUserService.selectUserById(verityTaskSysUsers.get(0).getVerityUserId());
                t.setVerityUser(sysUser.getUserName());
            }
            Long taskClazz = t.getTaskClazz();
            LabelStatistics labelStatistics = null;
            if (0L == taskClazz){
                VoiceAnnotation voiceAnnotation =new VoiceAnnotation();
                voiceAnnotation.setLabelUser(t.getUserId());
                voiceAnnotation.setTaskId(t.getTaskId());
                labelStatistics = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
                voiceAnnotation.setIsMark("是");
//                labeledNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
//                verityNum = voiceAnnotationService.selectVoiceAnnotationVerityCount(voiceAnnotation);
//                wordAccuracy = voiceAnnotationService.selectVoiceAnnotationWordAccuracy(voiceAnnotation);
                voiceAnnotation.setIsPass(1);
//                passNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
//                recall = voiceAnnotationService.selectVoiceAnnotationRecall(voiceAnnotation);
            }else if(1L == taskClazz){
                AsrResult1 asrResult1 = new AsrResult1();
                asrResult1.setLabelUser(t.getUserId());
                asrResult1.setTaskId(t.getTaskId());
                labelStatistics = asrResult1Service.selectAsrResult1Count(asrResult1);
            }

            if (labelStatistics!=null){
                t.setLabelNum(String.valueOf(labelStatistics.getLabelNum()));
                t.setVerityNum(String.valueOf(labelStatistics.getVerityNum()));
                t.setWordAccuracy(labelStatistics.getWordAccuracy());
                if (labelStatistics.getLabelNum()>0)
                    t.setNumberAccuracy(1.0*labelStatistics.getPassNum()/labelStatistics.getLabelNum());
                t.setRecallNum(labelStatistics.getRecallNum());
                t.setLabeledNum(String.valueOf(labelStatistics.getLabeledNum()));
            }

        }
        return taskUserTaskAllocations;
    }

    /**
     * 查询任务用户列表
     * @param taskUserTaskAllocation 任务用户
     * @return 任务用户
     */
    @Override
    public List<TaskUserTaskAllocation> selectTaskUserVerityTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        List<TaskUserTaskAllocation> taskUserTaskAllocations = taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        for (TaskUserTaskAllocation t:taskUserTaskAllocations) {
            VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
            verityTaskSysUser.setTaskId(t.getTaskId());
            verityTaskSysUser.setLabelUserId(t.getUserId());
            List<VerityTaskSysUser> verityTaskSysUsers = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUser);
            if(verityTaskSysUsers.size()>0){
                SysUser sysUser = sysUserService.selectUserById(verityTaskSysUsers.get(0).getVerityUserId());
                t.setVerityUser(sysUser.getUserName());
            }
            Long taskClazz = t.getTaskClazz();
            LabelStatistics labelStatistics = null;
            if (0L == taskClazz){
                VoiceAnnotation voiceAnnotation =new VoiceAnnotation();
                voiceAnnotation.setLabelUser(t.getUserId());
                voiceAnnotation.setTaskId(t.getTaskId());
                labelStatistics = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
                voiceAnnotation.setIsMark("是");
//                labeledNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
//                verityNum = voiceAnnotationService.selectVoiceAnnotationVerityCount(voiceAnnotation);
//                wordAccuracy = voiceAnnotationService.selectVoiceAnnotationWordAccuracy(voiceAnnotation);
                voiceAnnotation.setIsPass(1);
//                passNum = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation);
//                recall = voiceAnnotationService.selectVoiceAnnotationRecall(voiceAnnotation);
            }else if(1L == taskClazz){
                AsrResult1 asrResult1 = new AsrResult1();
                asrResult1.setLabelUser(t.getUserId());
                asrResult1.setTaskId(t.getTaskId());
                labelStatistics = asrResult1Service.selectAsrResult1Count(asrResult1);
            }

            if (labelStatistics!=null){
                t.setLabelNum(String.valueOf(labelStatistics.getLabelNum()));
                t.setVerityNum(String.valueOf(labelStatistics.getVerityNum()));
                t.setWordAccuracy(labelStatistics.getWordAccuracy());
                if (labelStatistics.getLabelNum()>0)
                    t.setNumberAccuracy(1.0*labelStatistics.getPassNum()/labelStatistics.getLabelNum());
                t.setRecallNum(labelStatistics.getRecallNum());
                t.setLabeledNum(String.valueOf(labelStatistics.getLabeledNum()));
            }

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
        if (verityTaskAllocationReq.getTaskClazz()!=null && verityTaskAllocationReq.getTaskClazz()==0){
            VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
            voiceAnnotation.setLabelUser(verityTaskAllocationReq.getLabelUserId());
            voiceAnnotation.setTaskId(verityTaskAllocationReq.getTaskId());
            int num = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation).getLabelNum();
            Long verityNum = verityTaskAllocationReq.getVerityNum();
            if (verityNum==null || verityNum == 0){
                Double verityPercentage = verityTaskAllocationReq.getVerityPercentage();
                if (verityPercentage == null ||verityPercentage==0)
                    throw new ServiceException("审核数量为空");
                verityTaskAllocationReq.setVerityNum((long) (num*verityPercentage));
            }
            if (num<verityNum)throw new RuntimeException("分配审核数量大于实际标注数量");

            VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
//            long id = verityTaskAllocationReq.getId();
            VerityTaskSysUser verityTaskSysUserSearch =new VerityTaskSysUser();
            verityTaskSysUserSearch.setTaskId(verityTaskAllocationReq.getTaskId());
            verityTaskSysUserSearch.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
            List<VerityTaskSysUser> verityTaskSysUsers = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUserSearch);
            VerityTaskSysUser verityTaskSysUser1 = null;
            if (verityTaskSysUsers!=null && verityTaskSysUsers.size()>0){
                verityTaskSysUser1 = verityTaskSysUsers.get(0);
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
            }else {
                VerityTaskSysUser verityTaskSysUser2 = new VerityTaskSysUser();
                verityTaskSysUser2.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
                verityTaskSysUser2.setTaskId(verityTaskAllocationReq.getTaskId());
                verityTaskSysUser2.setVerityUserId(verityTaskAllocationReq.getVerityUserId());
                verityTaskSysUser2.setCreateTime(DateUtils.getNowDate());
                verityTaskSysUser2.setUpdateTime(DateUtils.getNowDate());
                String reqUser = verityTaskAllocationReq.getReqUser();
                SysUser sysUser = sysUserService.selectUserByUserName(reqUser);
                verityTaskSysUser2.setReqUser(sysUser.getUserId());
                verityTaskSysUser1 = verityTaskSysUser2;
                verityTaskSysUser2.setExtractNum(verityNum);
                verityTaskSysUserService.insertVerityTaskSysUser(verityTaskSysUser2);
                verityTaskSysUser1.setExtractNum(0L);
            }

            Long reqNum = verityTaskAllocationReq.getVerityNum();
            int verityNum1 = voiceAnnotationService.selectVoiceAnnotationCount(voiceAnnotation).getRecallNum();
            if (verityTaskSysUser.getVerityUserId()!=null && verityTaskSysUser1.getVerityUserId() != verityTaskSysUser.getVerityUserId()){
                VoiceAnnotation v = new VoiceAnnotation();
                v.setVerityUser(verityTaskSysUser.getVerityUserId());
                v.setTaskId(verityTaskAllocationReq.getTaskId());
                v.setLabelUser(verityTaskAllocationReq.getLabelUserId());
                voiceAnnotationService.updateVoiceAnnotationVerityUser(v);
            }

            if (reqNum >verityNum1){
                AddVerityUser addVerityUser = new AddVerityUser();
                addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
                addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
                addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
                addVerityUser.setVerityNum(verityTaskAllocationReq.getVerityNum()-verityTaskSysUser1.getExtractNum());
                voiceAnnotationService.addVerity(addVerityUser);
            }else if (reqNum <verityNum1&&reqNum>0){
                AddVerityUser addVerityUser = new AddVerityUser();
                addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
                addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
                addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
                addVerityUser.setVerityNum(verityTaskSysUser1.getExtractNum()-verityTaskAllocationReq.getVerityNum());
                voiceAnnotationService.subtractVerity(addVerityUser);
            }
            return 1;
        } else if (verityTaskAllocationReq.getTaskClazz()!=null && verityTaskAllocationReq.getTaskClazz()==1) {
            AsrResult1 asrResult1 = new AsrResult1();
            asrResult1.setLabelUser(verityTaskAllocationReq.getLabelUserId());
            asrResult1.setTaskId(verityTaskAllocationReq.getTaskId());
            int num = asrResult1Service.selectAsrResult1Count(asrResult1).getLabelNum();
            Long verityNum = verityTaskAllocationReq.getVerityNum();
            if (verityNum==null || verityNum == 0){
                Double verityPercentage = verityTaskAllocationReq.getVerityPercentage();
                if (verityPercentage == null ||verityPercentage==0)
                    throw new ServiceException("审核数量为空");
                verityTaskAllocationReq.setVerityNum((long) (num*verityPercentage));
            }
            if (num<verityNum)
                throw new ServiceException("分配审核数量大于实际标注数量");

            VerityTaskSysUser verityTaskSysUser = new VerityTaskSysUser();
            long id = verityTaskAllocationReq.getId();
            VerityTaskSysUser verityTaskSysUserSearch =new VerityTaskSysUser();
            verityTaskSysUserSearch.setTaskId(verityTaskAllocationReq.getTaskId());
            verityTaskSysUserSearch.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
            List<VerityTaskSysUser> verityTaskSysUsers = verityTaskSysUserService.selectVerityTaskSysUserList(verityTaskSysUserSearch);
            VerityTaskSysUser verityTaskSysUser1 = null;
            if (verityTaskSysUsers!=null && verityTaskSysUsers.size()>0){
                verityTaskSysUser1 = verityTaskSysUsers.get(0);
                verityTaskSysUser.setTaskId(verityTaskAllocationReq.getTaskId());
                verityTaskSysUser.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
                verityTaskSysUser.setVerityUserId(verityTaskAllocationReq.getVerityUserId());
                String reqUser = verityTaskAllocationReq.getReqUser();
                SysUser sysUser = sysUserService.selectUserByUserName(reqUser);
                verityTaskSysUser.setReqUser(sysUser.getUserId());
                verityTaskSysUser.setExtractNum(verityNum);
                verityTaskSysUserService.updateVerityTaskSysUserByTaskIdAndLabelUserId(verityTaskSysUser);
            }else {
                VerityTaskSysUser verityTaskSysUser2 = new VerityTaskSysUser();
                verityTaskSysUser2.setLabelUserId(verityTaskAllocationReq.getLabelUserId());
                verityTaskSysUser2.setTaskId(verityTaskAllocationReq.getTaskId());
                verityTaskSysUser2.setVerityUserId(verityTaskAllocationReq.getVerityUserId());
                verityTaskSysUser2.setCreateTime(DateUtils.getNowDate());
                verityTaskSysUser2.setUpdateTime(DateUtils.getNowDate());
                String reqUser = verityTaskAllocationReq.getReqUser();
                SysUser sysUser = sysUserService.selectUserByUserName(reqUser);
                verityTaskSysUser2.setReqUser(sysUser.getUserId());
                verityTaskSysUser1 = verityTaskSysUser2;
                verityTaskSysUser2.setExtractNum(verityNum);
                verityTaskSysUserService.insertVerityTaskSysUser(verityTaskSysUser2);
            }
//            如果本次请求的审核人和之前的不同，则更改审核用户
            if (verityTaskSysUser.getVerityUserId() !=null && verityTaskSysUser1.getVerityUserId() != verityTaskSysUser.getVerityUserId()){
                AsrResult1 a = new AsrResult1();
                a.setVerityUser(verityTaskSysUser.getVerityUserId());
                a.setTaskId(verityTaskAllocationReq.getTaskId());
                a.setLabelUser(verityTaskAllocationReq.getLabelUserId());

                asrResult1Service.updateAsrResult1VerityUser(a);
            }
            Long reqNum = verityTaskAllocationReq.getVerityNum();
            int verityNum1 = asrResult1Service.selectAsrResult1Count(asrResult1).getRecallNum();
//            添加或者减少审核数量
            if (reqNum >verityNum1){
                AddVerityUser addVerityUser = new AddVerityUser();
                addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
                addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
                addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
                addVerityUser.setVerityNum(reqNum-verityNum1);
                asrResult1Service.addVerity(addVerityUser);
            }else if (reqNum < verityNum1&&reqNum>=0){
                AddVerityUser addVerityUser = new AddVerityUser();
                addVerityUser.setLabelUser(verityTaskAllocationReq.getLabelUserId());
                addVerityUser.setTaskId(verityTaskAllocationReq.getTaskId());
                addVerityUser.setVerityUser(verityTaskAllocationReq.getVerityUserId());
                addVerityUser.setVerityNum(verityNum1-reqNum);
                asrResult1Service.subtractVerity(addVerityUser);
            }
            return 1;
        }


        return 0;

    }
}
