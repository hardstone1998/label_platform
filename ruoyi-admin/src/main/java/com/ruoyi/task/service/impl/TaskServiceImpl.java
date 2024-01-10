package com.ruoyi.task.service.impl;

import java.util.List;

import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.asr.mapper.VoiceAnnotationMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.qa.domain.Class1;
import com.ruoyi.qa.mapper.AsrResult1Mapper;
import com.ruoyi.qa.mapper.Class1Mapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.task.domain.RequestTask;
import com.ruoyi.task.domain.Task;
import com.ruoyi.task.domain.TaskUserTaskAllocation;
import com.ruoyi.task.domain.TaskAllocationUser;
import com.ruoyi.task.mapper.TaskMapper;
import com.ruoyi.task.mapper.TaskUserTaskAllocationMapper;
import com.ruoyi.task.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-01-02
 */
@Slf4j
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AsrResult1Mapper asrResult1Mapper;

    @Autowired
    private VoiceAnnotationMapper voiceAnnotationMapper;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private Class1Mapper class1Mapper;

    @Autowired
    private TaskUserTaskAllocationMapper taskUserTaskAllocationMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param task 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        List<Task> tasks = taskMapper.selectTaskList(task);
        for (Task task1 :tasks){
            if (task1.getAllocationTotal()!=null&&task1.getAllocationTotal()!=0L)
            task1.setRecallRate((1.0*task1.getRecallNum())/task1.getAllocationTotal());
        }
        return tasks;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param task 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTask(RequestTask task)
    {
        log.info("任务分配"+task);
        Task task1 = new Task();
        Long clazz =task.getClazz();
        task1.setClazz(clazz);
        String task_name = task.getName();
        if (task_name ==null || task_name.length()==0){
            return -1;
        }
        task1.setName(task_name);
        task1.setDesc(task.getDesc());
        task1.setCreateTime(DateUtils.getNowDate());
        task1.setUpdateTime(DateUtils.getNowDate());

        int i = taskMapper.insertTask(task1);
        if (i<=0)return -1;
        List<TaskAllocationUser> taskAllocationUserRows = task.getTaskAllocationUserRows();
        int num = 0;
        for (TaskAllocationUser taskAllocationUser : taskAllocationUserRows){
            List<Long> selectedUsers = taskAllocationUser.getSelectedUsers();
            for (Long userId :selectedUsers){
                SysUser user = userService.selectUserById(userId);
                Class1 class1 = new Class1();
                class1.setValue(taskAllocationUser.getSevalue());
                List<Class1> class1s = class1Mapper.selectClass1List(class1);
                Long class1Id = 1L;
                int task_id = task1.getId();
                if(class1s.size()!=0){
                    class1Id = class1s.get(0).getId();
                }
                if (0L == clazz){
                    VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
                    voiceAnnotation.setLabelUser(userId);
                    voiceAnnotation.setUpdateNum(taskAllocationUser.getTaskQuantity());
                    voiceAnnotation.setTaskId(task_id);
                    voiceAnnotation.setTaskOwner(user.getUserName());
                    voiceAnnotation.setClazzId(class1Id);
                    voiceAnnotationMapper.updateVoiceAnnotationByClazzId(voiceAnnotation);
                }else if(1L == clazz){
//                    添加iu该task_owner逻辑
                    AsrResult1 asrResult1 = new AsrResult1();
                    asrResult1.setLabelUser(userId);
                    asrResult1.setUpdateNum(taskAllocationUser.getTaskQuantity());
                    asrResult1.setTaskId(task_id);
                    asrResult1.setTaskOwner(user.getUserName());
                    asrResult1.setClazzId(class1Id);
                    asrResult1Mapper.updateAsrResult1ByClazzId(asrResult1);
                }else {
                    return -1;
                }
                TaskUserTaskAllocation taskUserTaskAllocation = new TaskUserTaskAllocation();
                taskUserTaskAllocation.setTaskId((long)task_id);
                taskUserTaskAllocation.setUserId(userId);
                taskUserTaskAllocationMapper.insertTaskUserTaskAllocation(taskUserTaskAllocation);
                num++;
            }
        }
        log.info("任务分配完成");
        return num;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param task 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }
}
