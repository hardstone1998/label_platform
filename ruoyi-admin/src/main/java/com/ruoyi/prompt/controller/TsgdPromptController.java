package com.ruoyi.prompt.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.prompt.domain.TsgdPrompt;
import com.ruoyi.prompt.service.ITsgdPromptService;
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
 * @date 2023-11-29
 */
@RestController
@RequestMapping("/system/prompt")
public class TsgdPromptController extends BaseController
{
    @Autowired
    private ITsgdPromptService tsgdPromptService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:list')")
    @GetMapping("/list")
    public TableDataInfo list(TsgdPrompt tsgdPrompt)
    {
        startPage();
        List<TsgdPrompt> list = tsgdPromptService.selectTsgdPromptList(tsgdPrompt);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TsgdPrompt tsgdPrompt)
    {
        List<TsgdPrompt> list = tsgdPromptService.selectTsgdPromptList(tsgdPrompt);
        ExcelUtil<TsgdPrompt> util = new ExcelUtil<TsgdPrompt>(TsgdPrompt.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(tsgdPromptService.selectTsgdPromptById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TsgdPrompt tsgdPrompt)
    {
        return toAjax(tsgdPromptService.insertTsgdPrompt(tsgdPrompt));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TsgdPrompt tsgdPrompt)
    {
        return toAjax(tsgdPromptService.updateTsgdPrompt(tsgdPrompt));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tsgdPromptService.deleteTsgdPromptByIds(ids));
    }
}
