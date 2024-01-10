package com.ruoyi.task.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.List;

/**
 * @author：乔石磊
 * @date：14:47 2024/1/5
 */
@Data
public class RequestTask {

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

    private List<TaskAllocationUser> taskAllocationUserRows;
}
