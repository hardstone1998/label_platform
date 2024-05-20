package com.ruoyi.gugong.asr.mapper;

import java.util.List;
import com.ruoyi.gugong.asr.domain.AsrTagRelation;

/**
 * relationMapper接口
 *
 * @author ruoyi
 * @date 2023-06-29
 */
public interface AsrTagRelationMapper
{
    /**
     * 查询relation
     *
     * @param id relation主键
     * @return relation
     */
    public AsrTagRelation selectAsrTagRelationById(Long id);

    public List<AsrTagRelation> selectAsrTagRelByAsrId(Long asrId);



    public int deleteTagRelByAsrId(Long asrId);
    /**
     * 查询relation列表
     *
     * @param asrTagRelation relation
     * @return relation集合
     */
    public List<AsrTagRelation> selectAsrTagRelationList(AsrTagRelation asrTagRelation);

    /**
     * 新增relation
     *
     * @param asrTagRelation relation
     * @return 结果
     */
    public int insertAsrTagRelation(AsrTagRelation asrTagRelation);

    /**
     * 修改relation
     *
     * @param asrTagRelation relation
     * @return 结果
     */
    public int updateAsrTagRelation(AsrTagRelation asrTagRelation);

    /**
     * 删除relation
     *
     * @param id relation主键
     * @return 结果
     */
    public int deleteAsrTagRelationById(Long id);

    /**
     * 批量删除relation
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAsrTagRelationByIds(Long[] ids);
}
