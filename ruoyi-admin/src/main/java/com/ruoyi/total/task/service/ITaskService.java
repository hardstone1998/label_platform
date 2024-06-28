package com.ruoyi.total.task.service;

import com.ruoyi.total.task.domain.RequestTask;
import com.ruoyi.total.task.domain.Task;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-01-02
 */
public interface ITaskService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Task selectTaskById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param task 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增【请填写功能名称】
     *
     * @param task 【请填写功能名称】
     * @return 结果
     */
    public int insertTask(RequestTask task);

    /**
     * 一键分配功能
     *
     * @param task 【请填写功能名称】
     * @return 结果
     */
    public int allocationTask(Long id ,RequestTask task);

    /**
     * 修改【请填写功能名称】
     *
     * @param task 【请填写功能名称】
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTaskById(Long id);
}