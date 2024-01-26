package com.ruoyi.task.service.impl;

import java.util.Collections;
import java.util.Comparator;
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
import com.ruoyi.task.domain.*;
import com.ruoyi.task.mapper.TaskMapper;
import com.ruoyi.task.mapper.TaskUserTaskAllocationMapper;
import com.ruoyi.task.service.ITaskService;
import com.ruoyi.task.service.IUnallocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IUnallocationService unallocationService;

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
    @Transactional
    @Override
    public int insertTask(RequestTask task)
    {
        log.info("任务分配"+task);
        Task task1 = new Task();
        Long clazz =task.getClazz();
        task1.setClazz(clazz);
        String task_name = task.getName();
        if (task_name ==null || task_name.length()==0){
            throw new RuntimeException("任务名为空");
        }
        task1.setName(task_name);
        task1.setDesc(task.getDesc());
        task1.setCreateTime(DateUtils.getNowDate());
        task1.setUpdateTime(DateUtils.getNowDate());

        int i = taskMapper.insertTask(task1);
        if (i<=0)throw new RuntimeException("任务插入失败");
        List<TaskAllocationUser> taskAllocationUserRows = task.getTaskAllocationUser();
        int num = 0;
        for (TaskAllocationUser taskAllocationUser : taskAllocationUserRows){
            List<Long> selectedUsers = taskAllocationUser.getSelectedUsers();
            for (Long userId :selectedUsers){
                SysUser user = userService.selectUserById(userId);
                Class1 class1 = new Class1();
                class1.setValue(taskAllocationUser.getSevalue());
                List<Class1> class1s = class1Mapper.selectClass1List(class1);
                Long class1Id = 1L;
                int taskId = task1.getId();
                if(class1s.size()!=0){
                    class1Id = class1s.get(0).getId();
                }
                if (0L == clazz){
                    VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
                    voiceAnnotation.setLabelUser(userId);
                    voiceAnnotation.setUpdateNum(taskAllocationUser.getTaskQuantity());
                    voiceAnnotation.setTaskId((long)taskId);
                    voiceAnnotation.setTaskOwner(user.getUserName());
                    voiceAnnotation.setClazzId(class1Id);
                    voiceAnnotationMapper.updateVoiceAnnotationByClazzId(voiceAnnotation);
                }else if(1L == clazz){
//                    添加iu该task_owner逻辑
                    AsrResult1 asrResult1 = new AsrResult1();
                    asrResult1.setLabelUser(userId);
                    asrResult1.setUpdateNum(taskAllocationUser.getTaskQuantity());
                    asrResult1.setTaskId(taskId);
                    asrResult1.setTaskOwner(user.getUserName());
                    asrResult1.setClazzId(class1Id);
                    asrResult1Mapper.updateAsrResult1ByClazzId(asrResult1);
                }else {
                    throw new RuntimeException("类型错误");
                }
                TaskUserTaskAllocation taskUserTaskAllocation = new TaskUserTaskAllocation();
                taskUserTaskAllocation.setTaskId((long)taskId);
                taskUserTaskAllocation.setUserId(userId);
                List<TaskUserTaskAllocation> taskUserTaskAllocations = taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
                if (taskUserTaskAllocations.size()==0)
                    taskUserTaskAllocationMapper.insertTaskUserTaskAllocation(taskUserTaskAllocation);
                num++;
            }

        }
        log.info("任务分配完成");
        return num;
    }

    /**
     * 一键分配功能
     *
     * @param id 任务类型
     * @param task 任务
     * @return 结果
     */
    @Override
    @Transactional
    public int allocationTask(Long id, RequestTask task) {
        log.info("任务分配"+task);
        Task task1 = new Task();
        Long clazz =task.getClazz();
        task1.setClazz(clazz);
        String task_name = task.getName();
        if (task_name ==null || task_name.length()==0){
            throw new RuntimeException("任务名为空");
        }
        task1.setName(task_name);
        task1.setDesc(task.getDesc());
        task1.setCreateTime(DateUtils.getNowDate());
        task1.setUpdateTime(DateUtils.getNowDate());

        int i = taskMapper.insertTask(task1);
        int taskId = task1.getId();
        List<TaskAllocationUser> taskAllocationUser = task.getTaskAllocationUser();
        if (taskAllocationUser.isEmpty()) throw new RuntimeException("不存在用户信息");
        TaskAllocationUser taskAllocationUser1 = taskAllocationUser.get(0);
        if (i<=0)throw new RuntimeException("不存在用户信息");
        if (0L ==id){
            equalityAllocation(clazz,taskId,taskAllocationUser1);
        }else if(1L == id){
            sequenceAllocation(clazz,taskId,taskAllocationUser1);
        }else {
            throw new RuntimeException("任务类型错误");
        }
        return 1;
    }

    /**
     * 均等分配
     */
    private int equalityAllocation(Long clazz, int taskId, TaskAllocationUser taskUser){
        long taskQuantity = taskUser.getTaskQuantity();
        List<Long> selectedUsers = taskUser.getSelectedUsers();
        long userNum =selectedUsers.size();
        List<Unallocation> unallocations = unallocationService.selectUnallocationList(new Unallocation(null,null,userNum,0L==clazz?"asr":"qa"));
        Collections.sort(unallocations, new Comparator<Unallocation>() {
            @Override
            public int compare(Unallocation o1, Unallocation o2) {
                return (int) (o1.getNum() -o2.getNum());
            }
        });
        long clazzNum = unallocations.size();
        long residueNum = taskQuantity;
        if (0L == clazz){
            for (Unallocation unallocation:unallocations) {
                long num = unallocation.getNum();
                long avgUser = num/userNum;
                long avg = (long) Math.ceil(1.0*residueNum/clazzNum);
                long minAvg = Math.min(avgUser,avg);

                if (minAvg==0L)continue;
                residueNum -= minAvg;
                for (Long userId:selectedUsers) {
                    SysUser user = userService.selectUserById(userId);
                    VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
                    voiceAnnotation.setLabelUser(userId);
                    voiceAnnotation.setUpdateNum(minAvg);
                    voiceAnnotation.setTaskId((long) taskId);
                    voiceAnnotation.setTaskOwner(user.getUserName());
                    voiceAnnotation.setClazzId(unallocation.getClazzId());
                    System.out.println(voiceAnnotation);
                    voiceAnnotationMapper.updateVoiceAnnotationByClazzId(voiceAnnotation);
                }
            }
        }else if(1L == clazz){
            for (Unallocation unallocation:unallocations) {
                long num = unallocation.getNum();
                long avgUser = num / userNum;
                long avg = (long) Math.ceil(1.0*residueNum/clazzNum);
                long minAvg = Math.min(avgUser, avg);
                residueNum -= minAvg;
                for (Long userId : selectedUsers) {
                    SysUser user = userService.selectUserById(userId);
                    AsrResult1 asrResult1 = new AsrResult1();
                    asrResult1.setLabelUser(userId);
                    asrResult1.setUpdateNum(minAvg);
                    asrResult1.setTaskId(taskId);
                    asrResult1.setTaskOwner(user.getUserName());
                    asrResult1.setClazzId(unallocation.getClazzId());
                    asrResult1Mapper.updateAsrResult1ByClazzId(asrResult1);
                }
            }
        }else {
            throw new RuntimeException("类型错误");
        }
        return 1;
    }

    /**
     * 顺序分配
     */
    private int sequenceAllocation(Long clazz, int taskId, TaskAllocationUser taskUser){
        List<Long> selectedUsers = taskUser.getSelectedUsers();
        if (0L == clazz){
            for (Long userId:selectedUsers) {
                SysUser user = userService.selectUserById(userId);
                long taskQuantity = taskUser.getTaskQuantity();
                List<Unallocation> unallocations = unallocationService.selectUnallocationList(new Unallocation(null,null,null,"asr"));
                for (Unallocation unallocation:unallocations) {
                    Long num = unallocation.getNum();
                    VoiceAnnotation voiceAnnotation = new VoiceAnnotation();
                    voiceAnnotation.setLabelUser(userId);
                    voiceAnnotation.setUpdateNum(Math.min(taskQuantity,num));
                    voiceAnnotation.setTaskId((long)taskId);
                    voiceAnnotation.setTaskOwner(user.getUserName());
                    voiceAnnotation.setClazzId(unallocation.getClazzId());
                    voiceAnnotationMapper.updateVoiceAnnotationByClazzId(voiceAnnotation);
                    if (num>taskQuantity){
                        break;
                    }
                    taskQuantity-=num;
                }
            }
        }else if(1L == clazz){
            for (Long userId:selectedUsers) {
                SysUser user = userService.selectUserById(userId);
                long taskQuantity = taskUser.getTaskQuantity();
                List<Unallocation> unallocations = unallocationService.selectUnallocationList(new Unallocation(null,null,null,"qa"));
                for (Unallocation unallocation:unallocations) {
                    Long num = unallocation.getNum();
                    AsrResult1 asrResult1 = new AsrResult1();
                    asrResult1.setLabelUser(userId);
                    asrResult1.setUpdateNum(Math.min(taskQuantity,num));
                    asrResult1.setTaskId(taskId);
                    asrResult1.setTaskOwner(user.getUserName());
                    asrResult1.setClazzId(unallocation.getClazzId());
                    asrResult1Mapper.updateAsrResult1ByClazzId(asrResult1);
                    if (num>taskQuantity){
                        break;
                    }
                    taskQuantity-=num;
                }
            }
        }else {
            throw new RuntimeException("类型错误");
        }
        return 1;
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
