package com.ruoyi.gugong.qa.mapper;

import java.util.List;

import com.ruoyi.gugong.qa.domain.QaRelation;

/**
 * qa联系Mapper接口
 *
 * @author ruoyi
 * @date 2023-07-19
 */
public interface QaRelationMapper
{
    /**
     * 查询qa联系
     *
     * @param id qa联系主键
     * @return qa联系
     */
    public QaRelation selectQaRelationById(Long id);


    public QaRelation selectQaRelationByASRId(Long id);
    /**
     * 查询qa联系列表
     *
     * @param qaRelation qa联系
     * @return qa联系集合
     */
    public List<QaRelation> selectQaRelationList(QaRelation qaRelation);

    /**
     * 新增qa联系
     *
     * @param qaRelation qa联系
     * @return 结果
     */
    public int insertQaRelation(QaRelation qaRelation);

    /**
     * 修改qa联系
     *
     * @param qaRelation qa联系
     * @return 结果
     */
    public int updateQaRelation(QaRelation qaRelation);

    /**
     * 删除qa联系
     *
     * @param id qa联系主键
     * @return 结果
     */
    public int deleteQaRelationById(Long id);

    /**
     * 批量删除qa联系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQaRelationByIds(Long[] ids);
}
