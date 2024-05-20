package com.ruoyi.total.task.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.total.task.domain.Unallocation;
import com.ruoyi.total.task.service.IUnallocationService;
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
 * VIEWController
 *
 * @author ruoyi
 * @date 2024-01-16
 */
@RestController
@RequestMapping("/task/unallocation")
public class UnallocationController extends BaseController
{
    @Autowired
    private IUnallocationService unallocationService;

    /**
     * 查询VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Unallocation unallocation)
    {
        startPage();
        List<Unallocation> list = unallocationService.selectUnallocationList(unallocation);
        return getDataTable(list);
    }

    /**
     * 导出VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:export')")
    @Log(title = "VIEW", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Unallocation unallocation)
    {
        List<Unallocation> list = unallocationService.selectUnallocationList(unallocation);
        ExcelUtil<Unallocation> util = new ExcelUtil<Unallocation>(Unallocation.class);
        util.exportExcel(response, list, "VIEW数据");
    }

    /**
     * 获取VIEW详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:query')")
    @GetMapping(value = "/{clazzId}")
    public AjaxResult getInfo(@PathVariable("clazzId") Long clazzId)
    {
        return success(unallocationService.selectUnallocationByClazzId(clazzId));
    }

    /**
     * 新增VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:add')")
    @Log(title = "VIEW", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Unallocation unallocation)
    {
        return toAjax(unallocationService.insertUnallocation(unallocation));
    }

    /**
     * 修改VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:edit')")
    @Log(title = "VIEW", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Unallocation unallocation)
    {
        return toAjax(unallocationService.updateUnallocation(unallocation));
    }

    /**
     * 删除VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:unallocation:remove')")
    @Log(title = "VIEW", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clazzIds}")
    public AjaxResult remove(@PathVariable Long[] clazzIds)
    {
        return toAjax(unallocationService.deleteUnallocationByClazzIds(clazzIds));
    }
}
