package com.ruoyi.total.total.service;

import com.ruoyi.total.total.domain.InsertBatch;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-02-26
 */
public interface IInsertBatchService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public InsertBatch selectInsertBatchById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param insertBatch 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<InsertBatch> selectInsertBatchList(InsertBatch insertBatch);

    /**
     * 新增【请填写功能名称】
     *
     * @param insertBatch 【请填写功能名称】
     * @return 结果
     */
    public int insertInsertBatch(InsertBatch insertBatch);

    /**
     * 修改【请填写功能名称】
     *
     * @param insertBatch 【请填写功能名称】
     * @return 结果
     */
    public int updateInsertBatch(InsertBatch insertBatch);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteInsertBatchByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteInsertBatchById(Long id);
}
