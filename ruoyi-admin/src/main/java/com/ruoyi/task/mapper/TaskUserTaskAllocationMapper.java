package com.ruoyi.task.mapper;

import com.ruoyi.task.domain.TaskUserTaskAllocation;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-01-08
 */
public interface TaskUserTaskAllocationMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TaskUserTaskAllocation selectTaskUserTaskAllocationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param taskUserTaskAllocation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TaskUserTaskAllocation> selectTaskUserTaskAllocationList(TaskUserTaskAllocation taskUserTaskAllocation);

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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTaskUserTaskAllocationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskUserTaskAllocationByIds(Long[] ids);
}