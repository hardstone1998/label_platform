package com.ruoyi.geo.qa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.geo.qa.domain.GeoBookQa;
import com.ruoyi.geo.qa.service.IGeoBookQaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-05-20
 */
@RestController
@RequestMapping("/geo/qa")
public class GeoBookQaController extends BaseController
{
    @Autowired
    private IGeoBookQaService geoBookQaService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:list')")
    @GetMapping("/list")
    public TableDataInfo list(GeoBookQa geoBookQa)
    {
        startPage();
        List<GeoBookQa> list = geoBookQaService.selectGeoBookQaList(geoBookQa);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GeoBookQa geoBookQa)
    {
        List<GeoBookQa> list = geoBookQaService.selectGeoBookQaList(geoBookQa);
        ExcelUtil<GeoBookQa> util = new ExcelUtil<GeoBookQa>(GeoBookQa.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(geoBookQaService.selectGeoBookQaById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GeoBookQa geoBookQa)
    {
        return toAjax(geoBookQaService.insertGeoBookQa(geoBookQa));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GeoBookQa geoBookQa)
    {
        return toAjax(geoBookQaService.updateGeoBookQa(geoBookQa));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:qa:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(geoBookQaService.deleteGeoBookQaByIds(ids));
    }
}
