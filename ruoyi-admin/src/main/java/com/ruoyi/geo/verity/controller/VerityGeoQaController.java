package com.ruoyi.geo.verity.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.geo.verity.service.IVerityGeoQaService;
import com.ruoyi.gugong.qa.domain.AsrResult1;
import com.ruoyi.gugong.verity.service.IVerityQaService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * qa审核接口
 *
 * @author ruoyi
 * @date 2024-01-12
 */
@RestController
@RequestMapping("/geo/verity/qa")
public class VerityGeoQaController extends BaseController
{
    @Autowired
    private IVerityGeoQaService verityQaService;

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
