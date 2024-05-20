package com.ruoyi.gugong.qa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.gugong.qa.domain.QaRelation;
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
import com.ruoyi.gugong.qa.service.IQaRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * qa联系Controller
 *
 * @author ruoyi
 * @date 2023-07-19
 */
@RestController
@RequestMapping("/qa/relation")
public class QaRelationController extends BaseController
{
    @Autowired
    private IQaRelationService qaRelationService;

    /**
     * 查询qa联系列表
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(QaRelation qaRelation)
    {
        startPage();
        List<QaRelation> list = qaRelationService.selectQaRelationList(qaRelation);
        return getDataTable(list);
    }

    /**
     * 导出qa联系列表
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:export')")
    @Log(title = "qa联系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QaRelation qaRelation)
    {
        List<QaRelation> list = qaRelationService.selectQaRelationList(qaRelation);
        ExcelUtil<QaRelation> util = new ExcelUtil<QaRelation>(QaRelation.class);
        util.exportExcel(response, list, "qa联系数据");
    }

    /**
     * 获取qa联系详细信息
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(qaRelationService.selectQaRelationById(id));
    }

    /**
     * 新增qa联系
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:add')")
    @Log(title = "qa联系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QaRelation qaRelation)
    {
        return toAjax(qaRelationService.insertQaRelation(qaRelation));
    }

    /**
     * 修改qa联系
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:edit')")
    @Log(title = "qa联系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QaRelation qaRelation)
    {
        AjaxResult ajaxResult = toAjax(qaRelationService.updateQaRelation(qaRelation));
        return ajaxResult;
    }

    /**
     * 删除qa联系
     */
    @PreAuthorize("@ss.hasPermi('qa:relation:remove')")
    @Log(title = "qa联系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(qaRelationService.deleteQaRelationByIds(ids));
    }
}
