package com.ruoyi.task.domain;

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
public class TaskUserTaskAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long taskId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 字符准确率 */
    @Excel(name = "字符准确率")
    private Long wordAccuracy;

    /** 个数准确率 */
    @Excel(name = "个数准确率")
    private Long numberAccuracy;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setWordAccuracy(Long wordAccuracy)
    {
        this.wordAccuracy = wordAccuracy;
    }

    public Long getWordAccuracy()
    {
        return wordAccuracy;
    }
    public void setNumberAccuracy(Long numberAccuracy)
    {
        this.numberAccuracy = numberAccuracy;
    }

    public Long getNumberAccuracy()
    {
        return numberAccuracy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("wordAccuracy", getWordAccuracy())
            .append("numberAccuracy", getNumberAccuracy())
            .toString();
    }
}
