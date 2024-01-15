package com.ruoyi.task.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 task_allocation
 *
 * @author ruoyi
 * @date 2024-01-02
 */
@Data
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 任务名 */
    @Excel(name = "任务名")
    private String name;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String desc;

    /** 任务类型。0-asr标注，1-glm标注 */
    @Excel(name = "任务类型。0-asr标注，1-glm标注")
    private Long clazz;


    /** 字符准确率 */
    @Excel(name = "字符准确率")
    private int wordAccuracy;

    /** 召回数 */
    @Excel(name = "召回数")
    private Long recallNum;

    /** 分配总数 */
    @Excel(name = "分配总数")
    private Long allocationTotal;

    /** 召回率 */
    @Excel(name = "召回率")
    private Double recallRate;


    /** 个数准确率 */
    @Excel(name = "个数准确率")
    private Long numberAccuracy;

    private Long responsiblePersonId;
}
