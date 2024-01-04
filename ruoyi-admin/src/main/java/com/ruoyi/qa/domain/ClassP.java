package com.ruoyi.qa.domain;

import java.util.List;

public class ClassP {
    private Long id;
    private String value;
    private String label;
    private List<ClassP> children;

    private List<Class1> options;

    public List<Class1> getOptions() {
        return options;
    }

    public void setOptions(List<Class1> options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ClassP> getChildren() {
        return children;
    }

    public void setChildren(List<ClassP> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ClassP{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", children=" + children +
                ", options=" + options +
                '}';
    }
}
