package com.ruoyi.gugong.asr.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.asr.domain.AsrTagRelation;
import com.ruoyi.gugong.asr.mapper.AsrTagRelationMapper;
import com.ruoyi.gugong.asr.service.IAsrTagRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * relationService业务层处理
 *
 * @author ruoyi
 * @date 2023-06-29
 */
@Service
public class AsrTagRelationServiceImpl implements IAsrTagRelationService
{
    @Autowired
    private AsrTagRelationMapper asrTagRelationMapper;

    /**
     * 查询relation
     *
     * @param id relation主键
     * @return relation
     */
    @Override
    public AsrTagRelation selectAsrTagRelationById(Long id)
    {
        return asrTagRelationMapper.selectAsrTagRelationById(id);
    }

    /**
     * 查询relation列表
     *
     * @param asrTagRelation relation
     * @return relation
     */
    @Override
    public List<AsrTagRelation> selectAsrTagRelationList(AsrTagRelation asrTagRelation)
    {
        return asrTagRelationMapper.selectAsrTagRelationList(asrTagRelation);
    }

    /**
     * 新增relation
     *
     * @param asrTagRelation relation
     * @return 结果
     */
    @Override
    public int insertAsrTagRelation(AsrTagRelation asrTagRelation)
    {
        asrTagRelation.setCreateTime(DateUtils.getNowDate());
        return asrTagRelationMapper.insertAsrTagRelation(asrTagRelation);
    }

    /**
     * 修改relation
     *
     * @param asrTagRelation relation
     * @return 结果
     */
    @Override
    public int updateAsrTagRelation(AsrTagRelation asrTagRelation)
    {
        return asrTagRelationMapper.updateAsrTagRelation(asrTagRelation);
    }

    /**
     * 批量删除relation
     *
     * @param ids 需要删除的relation主键
     * @return 结果
     */
    @Override
    public int deleteAsrTagRelationByIds(Long[] ids)
    {
        return asrTagRelationMapper.deleteAsrTagRelationByIds(ids);
    }

    /**
     * 删除relation信息
     *
     * @param id relation主键
     * @return 结果
     */
    @Override
    public int deleteAsrTagRelationById(Long id)
    {
        return asrTagRelationMapper.deleteAsrTagRelationById(id);
    }
}
