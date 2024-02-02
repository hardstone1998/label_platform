package com.ruoyi.qa.mapper;

import java.util.List;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.tool.domain.LabelStatistics;

/**
 * extractMapper接口
 *
 * @author ruoyi
 * @date 2023-07-13
 */
public interface AsrResult1Mapper
{
    /**
     * 查询extract
     *
     * @param id extract主键
     * @return extract
     */
    public AsrResult1 selectAsrResult1ById(Long id);

    //    selectAsrResult1Count
    /**
     * 查询统计
     *
     * @param asrResult1
     * @return extract
     */
    public LabelStatistics selectAsrResult1Count(AsrResult1 asrResult1);

    /**
     * 查询extract列表
     *
     * @param asrResult1 extract
     * @return extract集合
     */
    public List<AsrResult1> selectAsrResult1List(AsrResult1 asrResult1);


    public List<AsrResult1> selectAsrResult1ListByOwner(AsrResult1 asrResult1);


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

    public int updateAsrResult1ByClazzId(AsrResult1 asrResult1);





    /**
     * 删除extract
     *
     * @param id extract主键
     * @return 结果
     */
    public int deleteAsrResult1ById(Long id);

    /**
     * 批量删除extract
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAsrResult1ByIds(Long[] ids);

    List<AsrResult1> selectAsrResult1ListByJson(AsrResult1 asrResult1);
}
