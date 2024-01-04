package com.ruoyi.qa.service;

import java.util.List;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.qa.domain.ClassP;

/**
 * extractService接口
 * 
 * @author ruoyi
 * @date 2023-07-13
 */
public interface IAsrResult1Service 
{
    /**
     * 查询extract
     * 
     * @param id extract主键
     * @return extract
     */
    public AsrResult1 selectAsrResult1ById(Long id);

    /**
     * 查询extract列表
     * 
     * @param asrResult1 extract
     * @return extract集合
     */
    public List<AsrResult1> selectAsrResult1List(AsrResult1 asrResult1);

    /**
     * 新增extract
     * 
     * @param asrResult1 extract
     * @return 结果
     */
    public int insertAsrResult1(AsrResult1 asrResult1);

    /**
     * 修改extract
     * 
     * @param asrResult1 extract
     * @return 结果
     */
    public int updateAsrResult1(AsrResult1 asrResult1);

    /**
     * 批量删除extract
     * 
     * @param ids 需要删除的extract主键集合
     * @return 结果
     */
    public int deleteAsrResult1ByIds(Long[] ids);

    /**
     * 删除extract信息
     * 
     * @param id extract主键
     * @return 结果
     */
    public int deleteAsrResult1ById(Long id);

   String selectAsrResult1JsonList(AsrResult1 asrResult1);

    ClassP getOptions();
}
