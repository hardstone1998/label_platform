package com.ruoyi.geo.qa.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.geo.qa.domain.GeoBookClass;
import com.ruoyi.geo.qa.domain.GeoBookQa;
import com.ruoyi.geo.qa.mapper.GeoBookQaMapper;
import com.ruoyi.geo.qa.service.IGeoBookClassService;
import com.ruoyi.geo.qa.service.IGeoBookQaService;
import com.ruoyi.gugong.qa.domain.AsrResult1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class GeoBookQaServiceImpl implements IGeoBookQaService
{
    @Autowired
    private GeoBookQaMapper geoBookQaMapper;

    @Autowired
    private IGeoBookClassService geoBookClassService;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public GeoBookQa selectGeoBookQaById(Long id)
    {
        GeoBookQa geoBookQa = geoBookQaMapper.selectGeoBookQaById(id);
        Long bookClassId = geoBookQa.getBookClassId();
        GeoBookClass geoBookClass = geoBookClassService.selectGeoBookClassById(bookClassId);
        StringBuilder bookClazzNameBuilder = new StringBuilder();
        bookClazzNameBuilder.append(geoBookClass.getBookName());

        String[] classLevels = {geoBookClass.getClass1(), geoBookClass.getClass2(), geoBookClass.getClass3(), geoBookClass.getClass4()};
        for (String clazzLevel : classLevels) {
            if (clazzLevel != null && !clazzLevel.isEmpty()) {
                bookClazzNameBuilder.append(">>>>>>").append(clazzLevel);
            }
        }

        String bookClazzName = bookClazzNameBuilder.toString();
        geoBookQa.setBookClazz(bookClazzName);
        return geoBookQa;
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<GeoBookQa> selectGeoBookQaList(GeoBookQa geoBookQa)
    {
        if ("admin".equals(geoBookQa.getTaskOwner()))
            geoBookQa.setTaskOwner(null);
        return geoBookQaMapper.selectGeoBookQaList(geoBookQa);

    }

    /**
     * 新增【请填写功能名称】
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertGeoBookQa(GeoBookQa geoBookQa)
    {
        geoBookQa.setCreateTime(DateUtils.getNowDate());
        return geoBookQaMapper.insertGeoBookQa(geoBookQa);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param geoBookQa 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateGeoBookQa(GeoBookQa geoBookQa)
    {
        geoBookQa.setIsMark("是");
        geoBookQa.setLabelTime(new Date());
        return geoBookQaMapper.updateGeoBookQa(geoBookQa);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGeoBookQaByIds(Long[] ids)
    {
        return geoBookQaMapper.deleteGeoBookQaByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteGeoBookQaById(Long id)
    {
        return geoBookQaMapper.deleteGeoBookQaById(id);
    }
}
