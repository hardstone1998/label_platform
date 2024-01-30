package com.ruoyi.verity.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.verity.service.IVerityAsrService;
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
 * @date 2024-01-12
 */
@RestController
@RequestMapping("/verity/asr")
public class VerityAsrController extends BaseController
{
    @Autowired
    private IVerityAsrService verityAsrService;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('verity:asr:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoiceAnnotation voiceAnnotation)
    {
        String taskOwner = voiceAnnotation.getTaskOwner();
        SysUser sysUser = sysUserMapper.selectUserByUserName(taskOwner);
        voiceAnnotation.setVerityUser(sysUser.getUserId());
        voiceAnnotation.setTaskOwner(null);
        startPage();
        List<VoiceAnnotation> list = verityAsrService.selectVerityAsrList(voiceAnnotation);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('verity:asr:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VoiceAnnotation voiceAnnotation)
    {
        List<VoiceAnnotation> list = verityAsrService.selectVerityAsrList(voiceAnnotation);
        ExcelUtil<VoiceAnnotation> util = new ExcelUtil<VoiceAnnotation>(VoiceAnnotation.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('verity:asr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(verityAsrService.selectVerityAsrById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('verity:asr:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VoiceAnnotation voiceAnnotation)
    {
        return toAjax(verityAsrService.insertVerityAsr(voiceAnnotation));
    }

    /**
     * asr审核
     */
//    @PreAuthorize("@ss.hasPermi('verity:asr:edit')")
    @Log(title = "asr审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VoiceAnnotation voiceAnnotation)
    {
        return toAjax(verityAsrService.updateVerityAsr(voiceAnnotation));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('verity:asr:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(verityAsrService.deleteVerityAsrByIds(ids));
    }
}
