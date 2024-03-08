package com.ruoyi.verity.controller;

import com.ruoyi.asr.domain.VoiceAnnotation;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.verity.service.IVerityQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * qa审核接口
 *
 * @author ruoyi
 * @date 2024-01-12
 */
@RestController
@RequestMapping("/verity/qa")
public class VerityQaController extends BaseController
{
    @Autowired
    private IVerityQaService verityQaService;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询审核列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AsrResult1 asrResult1)
    {
        String taskOwner = asrResult1.getTaskOwner();
        SysUser sysUser = sysUserMapper.selectUserByUserName(taskOwner);
        asrResult1.setVerityUser(sysUser.getUserId());
        asrResult1.setTaskOwner(null);
        startPage();
        List<AsrResult1> asrResult1s = verityQaService.selectVerityQaList(asrResult1);
        return getDataTable(asrResult1s);
    }
    /**
     * QA审核
     */
//    @PreAuthorize("@ss.hasPermi('verity:qa:edit')")
    @Log(title = "QA审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AsrResult1 asrResult1)
    {
        return toAjax(verityQaService.updateVerityQa(asrResult1));
    }

}
