package com.ruoyi.gugong.asr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.gugong.asr.service.IAsrTagRelationService;
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
import com.ruoyi.gugong.asr.domain.AsrTagRelation;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * relationController
 *
 * @author ruoyi
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/asr/relation")
public class AsrTagRelationController extends BaseController
{
    @Autowired
    private IAsrTagRelationService asrTagRelationService;

    /**
     * 查询relation列表
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AsrTagRelation asrTagRelation)
    {
        startPage();
        List<AsrTagRelation> list = asrTagRelationService.selectAsrTagRelationList(asrTagRelation);
        return getDataTable(list);
    }

    /**
     * 导出relation列表
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:export')")
    @Log(title = "relation", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AsrTagRelation asrTagRelation)
    {
        List<AsrTagRelation> list = asrTagRelationService.selectAsrTagRelationList(asrTagRelation);
        ExcelUtil<AsrTagRelation> util = new ExcelUtil<AsrTagRelation>(AsrTagRelation.class);
        util.exportExcel(response, list, "relation数据");
    }

    /**
     * 获取relation详细信息
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(asrTagRelationService.selectAsrTagRelationById(id));
    }

    /**
     * 新增relation
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:add')")
    @Log(title = "relation", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AsrTagRelation asrTagRelation)
    {
        return toAjax(asrTagRelationService.insertAsrTagRelation(asrTagRelation));
    }

    /**
     * 修改relation
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:edit')")
    @Log(title = "relation", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AsrTagRelation asrTagRelation)
    {
        return toAjax(asrTagRelationService.updateAsrTagRelation(asrTagRelation));
    }

    /**
     * 删除relation
     */
    @PreAuthorize("@ss.hasPermi('asr:relation:remove')")
    @Log(title = "relation", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(asrTagRelationService.deleteAsrTagRelationByIds(ids));
    }
}
