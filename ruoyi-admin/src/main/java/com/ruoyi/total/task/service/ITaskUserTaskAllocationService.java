package com.ruoyi.total.task.service;

import com.ruoyi.total.task.domain.TaskUserTaskAllocation;
import com.ruoyi.total.task.domain.VerityTaskAllocationReq;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-01-10
 */
public interface ITaskUserTaskAllocationService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TaskUserTaskAllocation selectTaskUserTaskAllocationById(Long id);

    public List<TaskUserTaskAllocation> selectTaskAllocationListByUserAndProject(TaskUserTaskAllocation taskUserTaskAllocation);

    /**
     * 查询用户任务列表
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TaskUserTaskAllocation> selectTaskUserTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation);

    /**
     * 查询用户审核任务列表
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TaskUserTaskAllocation> selectTaskUserVerityTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation);

    /**
     * 新增【请填写功能名称】
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 结果
     */
    public int insertTaskUserTaskAllocation(TaskUserTaskAllocation taskUserTaskAllocation);

    /**
     * 修改【请填写功能名称】
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 结果
     */
    public int updateTaskUserTaskAllocation(TaskUserTaskAllocation taskUserTaskAllocation);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTaskUserTaskAllocationByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTaskUserTaskAllocationById(Long id);

    public int verityAllocation(VerityTaskAllocationReq verityTaskAllocationReq);
}
