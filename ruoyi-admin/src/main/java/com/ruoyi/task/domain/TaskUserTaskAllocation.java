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
    private String taskName;

    private Long userId;

    @Excel(name = "用户名")
    private String userName;

    @Excel(name = "用户昵称")
    private String nickName;

    @Excel(name = "任务类型")
    private Long taskClazz;

    @Excel(name = "准确率")
    private Double accuracy;

    /** 字符准确率 */
    @Excel(name = "字符准确率")
    private Double wordAccuracy;

    /** 个数准确率 */
    @Excel(name = "个数准确率")
    private Double numberAccuracy;

    /** 召回率 */
    @Excel(name = "召回率")
    private Double recallRate;


    /** 召回数 */
    private Double recallNum;


    private Long responsiblePersonId;

    private String responsiblePersonName;

    private Long verityUser;

    private String labelNum;

    private String verityNum;

}
