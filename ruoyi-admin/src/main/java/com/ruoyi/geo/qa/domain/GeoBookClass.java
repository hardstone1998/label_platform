package com.ruoyi.geo.qa.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 geo_book_class
 *
 * @author ruoyi
 * @date 2024-05-21
 */
@Data
public class GeoBookClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 一级标题 */
    @Excel(name = "一级标题")
    private String class1;

    /** 二级标题 */
    @Excel(name = "二级标题")
    private String class2;

    /** 三级标题 */
    @Excel(name = "三级标题")
    private String class3;

    /** 四级标题 */
    @Excel(name = "四级标题")
    private String class4;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updataTime;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String description;

}
