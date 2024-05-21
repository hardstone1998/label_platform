package com.ruoyi.geo.qa.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.geo.qa.domain.GeoBookClass;
import com.ruoyi.geo.qa.mapper.GeoBookClassMapper;
import com.ruoyi.geo.qa.service.IGeoBookClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-21
 */
@Service
public class GeoBookClassServiceImpl implements IGeoBookClassService
{
    @Autowired
    private GeoBookClassMapper geoBookClassMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GeoBookClass selectGeoBookClassById(Long id)
    {
        return geoBookClassMapper.selectGeoBookClassById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GeoBookClass> selectGeoBookClassList(GeoBookClass geoBookClass)
    {
        return geoBookClassMapper.selectGeoBookClassList(geoBookClass);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGeoBookClass(GeoBookClass geoBookClass)
    {
        geoBookClass.setCreateTime(DateUtils.getNowDate());
        return geoBookClassMapper.insertGeoBookClass(geoBookClass);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param geoBookClass 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGeoBookClass(GeoBookClass geoBookClass)
    {
        return geoBookClassMapper.updateGeoBookClass(geoBookClass);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGeoBookClassByIds(Long[] ids)
    {
        return geoBookClassMapper.deleteGeoBookClassByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGeoBookClassById(Long id)
    {
        return geoBookClassMapper.deleteGeoBookClassById(id);
    }
}
