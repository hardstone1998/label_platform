package com.ruoyi.total.total.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.total.total.domain.InsertBatch;
import com.ruoyi.total.total.mapper.InsertBatchMapper;
import com.ruoyi.total.total.service.IInsertBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-02-26
 */
@Service
public class InsertBatchServiceImpl implements IInsertBatchService
{
    @Autowired
    private InsertBatchMapper insertBatchMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public InsertBatch selectInsertBatchById(Long id)
    {
        return insertBatchMapper.selectInsertBatchById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param insertBatch 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<InsertBatch> selectInsertBatchList(InsertBatch insertBatch)
    {
        return insertBatchMapper.selectInsertBatchList(insertBatch);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param insertBatch 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertInsertBatch(InsertBatch insertBatch)
    {
        insertBatch.setCreateTime(DateUtils.getNowDate());
        return insertBatchMapper.insertInsertBatch(insertBatch);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param insertBatch 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateInsertBatch(InsertBatch insertBatch)
    {
        return insertBatchMapper.updateInsertBatch(insertBatch);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInsertBatchByIds(Long[] ids)
    {
        return insertBatchMapper.deleteInsertBatchByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInsertBatchById(Long id)
    {
        return insertBatchMapper.deleteInsertBatchById(id);
    }
}
