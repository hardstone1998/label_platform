package com.ruoyi.task.domain;

import lombok.Data;

import java.util.List;

/**
 * @author：乔石磊
 * @date：14:49 2024/1/5
 */
@Data
public class UserTask {

    private List<Long> selectedUsers;

    private String sevalue;

    private Long taskQuantity;
}
