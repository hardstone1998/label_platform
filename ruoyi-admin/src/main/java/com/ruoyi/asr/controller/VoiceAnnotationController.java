package com.ruoyi.asr.controller;

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
import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.asr.service.IVoiceAnnotationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标注Controller
 * 
 * @author wrh
 * @date 2023-06-25
 */
@RestController
@RequestMapping("/asr/annotation")
public class VoiceAnnotationController extends BaseController
{
    @Autowired
    private IVoiceAnnotationService voiceAnnotationService;

    /**
     * 查询标注列表
     */
    //@PreAuthorize("@ss.hasPermi('asr:annotation:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoiceAnnotation voiceAnnotation)
    {
        startPage();
        List<VoiceAnnotation> list = voiceAnnotationService.selectVoiceAnnotationList(voiceAnnotation);
        return getDataTable(list);
    }

    /**
     * 导出标注列表
     */
    //@PreAuthorize("@ss.hasPermi('asr:annotation:export')")
    @Log(title = "标注", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VoiceAnnotation voiceAnnotation)
    {
        List<VoiceAnnotation> list = voiceAnnotationService.selectVoiceAnnotationList(voiceAnnotation);

        ExcelUtil<VoiceAnnotation> util = new ExcelUtil<VoiceAnnotation>(VoiceAnnotation.class);
        util.exportExcel(response, list, "标注数据");
    }

    /**
     * 获取标注详细信息
     */
   // @PreAuthorize("@ss.hasPermi('asr:annotation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {

        return success(voiceAnnotationService.selectVoiceAnnotationById(id));
    }

    /**
     * 新增标注
     */
    //@PreAuthorize("@ss.hasPermi('asr:annotation:add')")
    @Log(title = "标注", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VoiceAnnotation voiceAnnotation)
    {
        return toAjax(voiceAnnotationService.insertVoiceAnnotation(voiceAnnotation));
    }

    /**
     * 修改标注
     */
    //@PreAuthorize("@ss.hasPermi('asr:annotation:edit')")
    @Log(title = "标注", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VoiceAnnotation voiceAnnotation)
    {
        return toAjax(voiceAnnotationService.updateVoiceAnnotation(voiceAnnotation));
    }

    /**
     * 删除标注
     */
    //@PreAuthorize("@ss.hasPermi('asr:annotation:remove')")
    @Log(title = "标注", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        System.out.println("删除编号为"+ids[0]);
        return toAjax(voiceAnnotationService.deleteVoiceAnnotationByIds(ids));

    }

    @PostMapping("/getData")
    public AjaxResult getData()
    {
        System.out.println("获取统计数据......");
        return success(voiceAnnotationService.selectVoiceAnnotationData());
    }


}
