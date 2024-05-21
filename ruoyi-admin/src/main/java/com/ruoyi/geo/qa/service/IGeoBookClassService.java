package com.ruoyi.geo.qa.service;

import com.ruoyi.geo.qa.domain.GeoBookClass;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-05-21
 */
public interface IGeoBookClassService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GeoBookClass selectGeoBookClassById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GeoBookClass> selectGeoBookClassList(GeoBookClass geoBookClass);

    /**
     * 新增【请填写功能名称】
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 结果
     */
    public int insertGeoBookClass(GeoBookClass geoBookClass);

    /**
     * 修改【请填写功能名称】
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 结果
     */
    public int updateGeoBookClass(GeoBookClass geoBookClass);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGeoBookClassByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGeoBookClassById(Long id);
}
