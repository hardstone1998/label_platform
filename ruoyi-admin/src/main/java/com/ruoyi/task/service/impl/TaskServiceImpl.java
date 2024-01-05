package com.ruoyi.task.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.qa.mapper.AsrResult1Mapper;
import com.ruoyi.task.domain.RequestTask;
import com.ruoyi.task.domain.Task;
import com.ruoyi.task.domain.UserTask;
import com.ruoyi.task.mapper.TaskMapper;
import com.ruoyi.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-01-02
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AsrResult1Mapper asrResult1Mapper;

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
        return taskMapper.selectTaskList(task);
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
        Task task1 = new Task();
        Long clazz =task.getClazz();
        task1.setClazz(clazz);
        task1.setName(task.getName());
        task1.setDesc(task.getDesc());
        task1.setCreateTime(DateUtils.getNowDate());
        task1.setUpdateTime(DateUtils.getNowDate());

        int i = taskMapper.insertTask(task1);
        List<UserTask> userTaskRows = task.getUserTaskRows();
        for (UserTask userTask :userTaskRows){
            List<Long> selectedUsers = userTask.getSelectedUsers();
            for (Long userId :selectedUsers){
                if (0L == clazz){

                }else if(1L == clazz){
                    AsrResult1 asrResult1 = new AsrResult1();
                    asrResult1.setLabelUser(userId);
                    asrResult1Mapper.updateAsrResult1ByClazzId(asrResult1);
                }else {
                    return -1;
                }
            }
        }
        return i;
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
