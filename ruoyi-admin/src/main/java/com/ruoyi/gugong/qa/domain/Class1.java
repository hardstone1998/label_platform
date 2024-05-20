package com.ruoyi.gugong.qa.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class Class1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String value;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String label;

    /** 一级分类id */
    @Excel(name = "一级分类id")
    private Long class1Id;

    /** 二级分类id */
    @Excel(name = "二级分类id")
    private Long class2Id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Class1> children;
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    public void setClass1Id(Long class1Id)
    {
        this.class1Id = class1Id;
    }

    public Long getClass1Id()
    {
        return class1Id;
    }
    public void setClass2Id(Long class2Id)
    {
        this.class2Id = class2Id;
    }

    public Long getClass2Id()
    {
        return class2Id;
    }

    public List<Class1> getChildren() {
        return children;
    }

    public void setChildren(List<Class1> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Class1{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", class1Id=" + class1Id +
                ", class2Id=" + class2Id +
                ", children=" + children +
                '}';
    }
}
