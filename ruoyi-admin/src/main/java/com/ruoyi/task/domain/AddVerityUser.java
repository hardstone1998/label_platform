package com.ruoyi.task.domain;

import lombok.Data;

/**
 * @author：乔石磊
 * @date：9:51 2024/1/16
 */
@Data
public class AddVerityUser {

    /** ID */
    private Long id;

    /** 任务ID */
    private Long taskId;

    /** 标注用户 */
    private Long labelUser;

    /** 审核用户 */
    private Long verityUser;

    /** 审核数量 */
    private Long verityNum;
}
