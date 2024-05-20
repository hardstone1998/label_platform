package com.ruoyi.other.prompt.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 tsgd_prompt
 *
 * @author ruoyi
 * @date 2023-11-29
 */
public class TsgdPrompt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 类别名 */
    @Excel(name = "类别名")
    private String name;

    /** 提示词内容 */
    @Excel(name = "提示词内容")
    private String prompt;

    /** 所属类别。0-信息整合，1-信息提取 */
    @Excel(name = "所属类别。0-信息整合，1-信息提取")
    private Integer clazz;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createName;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateName;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
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
    public void setPrompt(String prompt)
    {
        this.prompt = prompt;
    }

    public String getPrompt()
    {
        return prompt;
    }
    public void setClazz(Integer clazz)
    {
        this.clazz = clazz;
    }

    public Integer getClazz()
    {
        return clazz;
    }
    public void setCreateName(Date createName)
    {
        this.createName = createName;
    }

    public Date getCreateName()
    {
        return createName;
    }
    public void setUpdateName(Date updateName)
    {
        this.updateName = updateName;
    }

    public Date getUpdateName()
    {
        return updateName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("prompt", getPrompt())
            .append("clazz", getClazz())
            .append("createName", getCreateName())
            .append("updateName", getUpdateName())
            .toString();
    }
}
