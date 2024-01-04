package com.ruoyi.task.domain;

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
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 任务名 */
    @Excel(name = "任务名")
    private String name;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String desc;

    /** 任务类型。0-asr标注，1-glm标注 */
    @Excel(name = "任务类型。0-asr标注，1-glm标注")
    private Long clazz;

    /** 正确率 */
    @Excel(name = "正确率")
    private Long accuracy;

    /** 召回率 */
    @Excel(name = "召回率")
    private Long recallRate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bak1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bak2;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDesc()
    {
        return desc;
    }
    public void setClazz(Long clazz)
    {
        this.clazz = clazz;
    }

    public Long getClazz()
    {
        return clazz;
    }
    public void setAccuracy(Long accuracy)
    {
        this.accuracy = accuracy;
    }

    public Long getAccuracy()
    {
        return accuracy;
    }
    public void setRecallRate(Long recallRate)
    {
        this.recallRate = recallRate;
    }

    public Long getRecallRate()
    {
        return recallRate;
    }
    public void setBak1(String bak1)
    {
        this.bak1 = bak1;
    }

    public String getBak1()
    {
        return bak1;
    }
    public void setBak2(String bak2)
    {
        this.bak2 = bak2;
    }

    public String getBak2()
    {
        return bak2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("desc", getDesc())
            .append("clazz", getClazz())
            .append("accuracy", getAccuracy())
            .append("recallRate", getRecallRate())
            .append("bak1", getBak1())
            .append("bak2", getBak2())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
