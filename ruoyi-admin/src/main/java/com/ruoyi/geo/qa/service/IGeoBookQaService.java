package com.ruoyi.geo.qa.service;

import com.ruoyi.geo.qa.domain.GeoBookQa;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface IGeoBookQaService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public GeoBookQa selectGeoBookQaById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<GeoBookQa> selectGeoBookQaList(GeoBookQa geoBookQa);

    /**
     * 新增【请填写功能名称】
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 结果
     */
    public int insertGeoBookQa(GeoBookQa geoBookQa);

    /**
     * 修改【请填写功能名称】
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 结果
     */
    public int updateGeoBookQa(GeoBookQa geoBookQa);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteGeoBookQaByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGeoBookQaById(Long id);
}
