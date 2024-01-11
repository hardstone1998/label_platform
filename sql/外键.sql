ALTER TABLE `asr-identify`.`voice_annotation`
    MODIFY COLUMN `label_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标注人' AFTER `task_id`,
    MODIFY COLUMN `verity_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人' AFTER `label_user`,
    ADD CONSTRAINT `pk_voice_annotation_label_user_sys_user_user_name` FOREIGN KEY (`label_user`) REFERENCES `asr-identify`.`sys_user` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
ADD CONSTRAINT `pk_voice_annotation_verity_user_sys_user_user_name` FOREIGN KEY (`verity_user`) REFERENCES `asr-identify`.`sys_user` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT;
