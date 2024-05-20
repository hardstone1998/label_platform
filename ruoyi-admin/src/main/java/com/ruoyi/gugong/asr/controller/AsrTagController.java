package com.ruoyi.gugong.asr.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.gugong.asr.domain.AsrTag;
import com.ruoyi.gugong.asr.service.IAsrTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * tagsController
 *
 * @author ruoyi
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/asr/tag")
public class AsrTagController extends BaseController
{
    @Autowired
    private IAsrTagService asrTagService;

    /**
     * 查询tags列表
     */
   // @PreAuthorize("@ss.hasPermi('asr:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(AsrTag asrTag)
    {
        startPage();
        List<AsrTag> list = asrTagService.selectAsrTagList(asrTag);
        return getDataTable(list);
    }

    /**
     * 导出tags列表
     */
    @PreAuthorize("@ss.hasPermi('asr:tag:export')")
    @Log(title = "tags", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AsrTag asrTag)
    {
        List<AsrTag> list = asrTagService.selectAsrTagList(asrTag);
        ExcelUtil<AsrTag> util = new ExcelUtil<AsrTag>(AsrTag.class);
        util.exportExcel(response, list, "tags数据");
    }

    /**
     * 获取tags详细信息
     */
    //@PreAuthorize("@ss.hasPermi('asr:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(asrTagService.selectAsrTagById(id));
    }

    /**
     * 新增tags
     */
    //@PreAuthorize("@ss.hasPermi('asr:tag:add')")
    @Log(title = "tags", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AsrTag asrTag)
    {

        AjaxResult ajaxResult = toAjax(asrTagService.insertAsrTag(asrTag));
        return ajaxResult;
    }

    /**
     * 修改tags
     */
    @PreAuthorize("@ss.hasPermi('asr:tag:edit')")
    @Log(title = "tags", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AsrTag asrTag)
    {
        return toAjax(asrTagService.updateAsrTag(asrTag));
    }

    /**
     * 删除tags
     */
    @PreAuthorize("@ss.hasPermi('asr:tag:remove')")
    @Log(title = "tags", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(asrTagService.deleteAsrTagByIds(ids));
    }
}
