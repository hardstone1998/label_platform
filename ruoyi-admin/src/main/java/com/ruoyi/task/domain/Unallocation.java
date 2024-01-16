package com.ruoyi.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 unallocation
 *
 * @author ruoyi
 * @date 2024-01-16
 */
public class Unallocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别id */
    @Excel(name = "类别id")
    private Long clazzId;

    /** 类别名 */
    @Excel(name = "类别名")
    private String label;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private Long num;

    /** asr或Qa */
    @Excel(name = "asr或Qa")
    private String asrOrQa;

    public void setClazzId(Long clazzId)
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId()
    {
        return clazzId;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    public void setNum(Long num)
    {
        this.num = num;
    }

    public Long getNum()
    {
        return num;
    }
    public void setAsrOrQa(String asrOrQa)
    {
        this.asrOrQa = asrOrQa;
    }

    public String getAsrOrQa()
    {
        return asrOrQa;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clazzId", getClazzId())
            .append("label", getLabel())
            .append("num", getNum())
            .append("asrOrQa", getAsrOrQa())
            .toString();
    }
}
