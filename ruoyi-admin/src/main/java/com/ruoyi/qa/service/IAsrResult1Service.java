package com.ruoyi.qa.service;

import java.util.List;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.qa.domain.ClassP;
import com.ruoyi.task.domain.AddVerityUser;
import com.ruoyi.tool.domain.LabelStatistics;

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

//    selectAsrResult1Count
    /**
     * 统计qa标注情况
     *
     * @param asrResult1 asrResult1条件
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
     * 通过任务id和标注人修改extract
     *
     * @param asrResult1 extract
     * @return 结果
     */
    public int updateAsrResult1ByTaskAndLabelUser(AsrResult1 asrResult1);

    /**
     * 修改标注 增加
     *
     * @param addVerityUser 标注
     * @return 结果
     */
    public int addVerity(AddVerityUser addVerityUser);

    /**
     * 修改标注 减少
     *
     * @param addVerityUser 标注
     * @return 结果
     */
    public int subtractVerity(AddVerityUser addVerityUser);

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

    /**
     * 导出方式2，自动关联qa1和qa2。同时qa1和qa2也会独立导出
     *
     * @param asrResult1 筛选信息
     * @return 结果
     */
    String selectAsrResult1JsonList2(AsrResult1 asrResult1);

   String selectAsrResult1JsonList(AsrResult1 asrResult1);

    ClassP getOptions();
}
