package com.ruoyi.gugong.qa.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * qa联系对象 qa_relation
 *
 * @author ruoyi
 * @date 2023-07-19
 */
public class QaRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long asrResultId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qa1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qa2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qa3;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qa4;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String qa5;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAsrResultId(Long asrResultId)
    {
        this.asrResultId = asrResultId;
    }

    public Long getAsrResultId()
    {
        return asrResultId;
    }
    public void setQa1(String qa1)
    {
        this.qa1 = qa1;
    }

    public String getQa1()
    {
        return qa1;
    }
    public void setQa2(String qa2)
    {
        this.qa2 = qa2;
    }

    public String getQa2()
    {
        return qa2;
    }
    public void setQa3(String qa3)
    {
        this.qa3 = qa3;
    }

    public String getQa3()
    {
        return qa3;
    }
    public void setQa4(String qa4)
    {
        this.qa4 = qa4;
    }

    public String getQa4()
    {
        return qa4;
    }
    public void setQa5(String qa5)
    {
        this.qa5 = qa5;
    }

    public String getQa5()
    {
        return qa5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("asrResultId", getAsrResultId())
                .append("qa1", getQa1())
                .append("qa2", getQa2())
                .append("qa3", getQa3())
                .append("qa4", getQa4())
                .append("qa5", getQa5())
                .append("createTime", getCreateTime())
                .toString();
    }
}
