package com.ruoyi.gugong.qa.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gugong.qa.domain.QaRelation;
import com.ruoyi.gugong.qa.mapper.QaRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gugong.qa.service.IQaRelationService;

/**
 * qa联系Service业务层处理
 *
 * @author ruoyi
 * @date 2023-07-19
 */
@Service
public class QaRelationServiceImpl implements IQaRelationService
{
    @Autowired
    private QaRelationMapper qaRelationMapper;

    /**
     * 查询qa联系
     *
     * @param id qa联系主键
     * @return qa联系
     */
    @Override
    public QaRelation selectQaRelationById(Long id)
    {
        return qaRelationMapper.selectQaRelationById(id);
    }

    /**
     * 查询qa联系列表
     *
     * @param qaRelation qa联系
     * @return qa联系
     */
    @Override
    public List<QaRelation> selectQaRelationList(QaRelation qaRelation)
    {
        return qaRelationMapper.selectQaRelationList(qaRelation);
    }

    /**
     * 新增qa联系
     *
     * @param qaRelation qa联系
     * @return 结果
     */
    @Override
    public int insertQaRelation(QaRelation qaRelation)
    {
        qaRelation.setCreateTime(DateUtils.getNowDate());
        return qaRelationMapper.insertQaRelation(qaRelation);
    }

    /**
     * 修改qa联系
     *
     * @param qaRelation qa联系
     * @return 结果
     */
    @Override
    public int updateQaRelation(QaRelation qaRelation)
    {
        if(qaRelation.getId()==null ){
            //新增关系
            try{
                qaRelation.setCreateTime(DateUtils.getNowDate());
                return qaRelationMapper.insertQaRelation(qaRelation);
            }catch (Exception e){
                return 0;
            }
        }
        int i = qaRelationMapper.updateQaRelation(qaRelation);
        if (i==0){
            return 123456;
        }else {
            return i;
        }
    }

    /**
     * 批量删除qa联系
     *
     * @param ids 需要删除的qa联系主键
     * @return 结果
     */
    @Override
    public int deleteQaRelationByIds(Long[] ids)
    {
        return qaRelationMapper.deleteQaRelationByIds(ids);
    }

    /**
     * 删除qa联系信息
     *
     * @param id qa联系主键
     * @return 结果
     */
    @Override
    public int deleteQaRelationById(Long id)
    {
        return qaRelationMapper.deleteQaRelationById(id);
    }
}
