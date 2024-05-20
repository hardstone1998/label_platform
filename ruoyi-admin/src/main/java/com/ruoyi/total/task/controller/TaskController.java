package com.ruoyi.total.task.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.total.task.domain.RequestTask;
import com.ruoyi.total.task.domain.Task;
import com.ruoyi.total.task.service.ITaskService;
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
 * @date 2024-01-02
 */
@RestController
@RequestMapping("/task/allocation")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Task Task)
    {
        startPage();
        List<Task> list = taskService.selectTaskList(Task);
        return getDataTable(list);
    }
    /**
     * 全部任务列表
     */
//    @PreAuthorize("@ss.hasPermi('system:allocation:all')")
    @PostMapping("/all")
    public TableDataInfo all(String responsiblePersonName)
    {

        if(responsiblePersonName==null ||responsiblePersonName.length()==0)return null;
        Task task = new Task();
        List<Task> list = null;

        if ("admin".equals(responsiblePersonName)) {
            list = taskService.selectTaskList(task);
        }else {
            SysUser sysUser = sysUserService.selectUserByUserName(responsiblePersonName);
            task.setResponsiblePersonId(sysUser.getUserId());
            taskService.selectTaskList(task);
        }
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Task task)
    {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskService.selectTaskById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RequestTask task)
    {
        int i = taskService.insertTask(task);
        if (i<=0)throw new ServiceException("分配任务失败");
        return toAjax(i);
    }

    /**
     * 一键分配功能
     *
     * @param id 任务类型 0为均匀分配，1为顺序分配。（均匀分配为每个用户各种类型任务相同，顺序分配为类型一全部分给用户1如果不够再将类型二全部分给用户1，用户2也是如此。以此类推）
     * @param task 任务
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:add')")
    @Log(title = "一键分配", businessType = BusinessType.INSERT)
    @PostMapping("/simple/{id}")
    public AjaxResult allocation(@PathVariable("id") Long id,@RequestBody RequestTask task)
    {
        int i = taskService.allocationTask(id,task);
        if (i<=0)throw new ServiceException("分配任务失败");
        return toAjax(i);
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Task task)
    {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:allocation:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskService.deleteTaskByIds(ids));
    }
}
