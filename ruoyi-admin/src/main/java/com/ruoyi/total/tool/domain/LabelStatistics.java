package com.ruoyi.total.tool.domain;

import lombok.Data;

/**
 * @author：乔石磊
 * @date：17:35 2024/2/2
 * asr和qa标注和审核统计
 */
@Data
public class LabelStatistics {

    public Integer labelNum;

    public Integer labeledNum;

    public Integer verityNum;

    public Integer passNum;

    public Double wordAccuracy;

    public Integer recallNum;
}
