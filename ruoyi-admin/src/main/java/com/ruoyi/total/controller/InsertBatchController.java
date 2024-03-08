package com.ruoyi.total.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.total.domain.InsertBatch;
import com.ruoyi.total.service.IInsertBatchService;
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
 * @date 2024-02-26
 */
@RestController
@RequestMapping("/total/batch")
public class InsertBatchController extends BaseController
{
    @Autowired
    private IInsertBatchService insertBatchService;

    /**
     * 查询插入批次列表
     */
//    @PreAuthorize("@ss.hasPermi('system:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(InsertBatch insertBatch)
    {
        List<InsertBatch> list = insertBatchService.selectInsertBatchList(insertBatch);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:batch:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InsertBatch insertBatch)
    {
        List<InsertBatch> list = insertBatchService.selectInsertBatchList(insertBatch);
        ExcelUtil<InsertBatch> util = new ExcelUtil<InsertBatch>(InsertBatch.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:batch:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(insertBatchService.selectInsertBatchById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:batch:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InsertBatch insertBatch)
    {
        return toAjax(insertBatchService.insertInsertBatch(insertBatch));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:batch:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InsertBatch insertBatch)
    {
        return toAjax(insertBatchService.updateInsertBatch(insertBatch));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:batch:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(insertBatchService.deleteInsertBatchByIds(ids));
    }
}
