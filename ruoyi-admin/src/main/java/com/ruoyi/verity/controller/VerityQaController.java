package com.ruoyi.verity.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.qa.domain.AsrResult1;
import com.ruoyi.verity.service.IVerityQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 【请填写功能名称】Controller
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



    /**
     * QA审核
     */
    @PreAuthorize("@ss.hasPermi('verity:qa:edit')")
    @Log(title = "QA审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AsrResult1 asrResult1)
    {

        return toAjax(verityQaService.updateVerityQa(asrResult1));
    }

}
