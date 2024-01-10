package com.ruoyi.task.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.task.domain.TaskUserTaskAllocation;
import com.ruoyi.task.service.ITaskUserTaskAllocationService;
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
 * @date 2024-01-10
 */
@RestController
@RequestMapping("/task/user")
public class TaskUserTaskAllocationController extends BaseController
{
    @Autowired
    private ITaskUserTaskAllocationService taskUserTaskAllocationService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('task:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskUserTaskAllocation taskUserTaskAllocation)
    {
        System.out.println("被调用");
        startPage();
        List<TaskUserTaskAllocation> list = taskUserTaskAllocationService.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('task:user:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskUserTaskAllocation taskUserTaskAllocation)
    {
        List<TaskUserTaskAllocation> list = taskUserTaskAllocationService.selectTaskUserTaskAllocationList(taskUserTaskAllocation);
        ExcelUtil<TaskUserTaskAllocation> util = new ExcelUtil<TaskUserTaskAllocation>(TaskUserTaskAllocation.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskUserTaskAllocationService.selectTaskUserTaskAllocationById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('task:user:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskUserTaskAllocation taskUserTaskAllocation)
    {
        return toAjax(taskUserTaskAllocationService.insertTaskUserTaskAllocation(taskUserTaskAllocation));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('task:user:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskUserTaskAllocation taskUserTaskAllocation)
    {
        return toAjax(taskUserTaskAllocationService.updateTaskUserTaskAllocation(taskUserTaskAllocation));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('task:user:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskUserTaskAllocationService.deleteTaskUserTaskAllocationByIds(ids));
    }
}