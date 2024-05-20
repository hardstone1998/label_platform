package com.ruoyi.geo.qa.mapper;

import com.ruoyi.geo.qa.domain.GeoBookQa;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-20
 */
public interface GeoBookQaMapper
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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteGeoBookQaById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGeoBookQaByIds(Long[] ids);
}
