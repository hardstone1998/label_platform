package com.ruoyi.task.service.impl;

import java.util.List;

import com.ruoyi.task.domain.TaskUserTaskAllocation;
import com.ruoyi.task.mapper.TaskUserTaskAllocationMapper;
import com.ruoyi.task.service.ITaskUserTaskAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 查询【请填写功能名称】列表
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TaskUserTaskAllocation> selectTaskUserTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        return taskUserTaskAllocationMapper.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
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
}
