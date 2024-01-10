package com.ruoyi.task.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 task_user_task_allocation
 *
 * @author ruoyi
 * @date 2024-01-08
 */
@Data
public class TaskUserTaskAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */

    private Long taskId;

    @Excel(name = "任务名")
    private Long taskName;

    private Long userId;

    @Excel(name = "用户名")
    private Long userName;

    @Excel(name = "任务类型")
    private Long taskClazz;

    @Excel(name = "准确率")
    private Long accuracy;

    /** 字符准确率 */
    @Excel(name = "字符准确率")
    private Long wordAccuracy;

    /** 个数准确率 */
    @Excel(name = "个数准确率")
    private Long numberAccuracy;

    @Excel(name = "召回率")
    private Long recallRate;

}
