package com.ruoyi.total.total.domain;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 answer
 *
 * @author ruoyi
 * @date 2024-03-05
 */
@Data
public class Answer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 知识类型 */
    @Excel(name = "知识类型")
    private String type;

    /** 问题 */
    @Excel(name = "问题")
    private String question;

    /** 知识内容 */
    @Excel(name = "知识内容")
    private String content;

    /** 类型id */
    @Excel(name = "类型id")
    private Long clazzId;

}
