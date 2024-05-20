package com.ruoyi.gugong.asr.domain;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JSONType(orders = {"audio"})
@NoArgsConstructor
public class ExportASRResJson {

    private Audio audio;

    @AllArgsConstructor
    @Data
    @JSONType(orders = {"audio"})
    public static class Audio{
        private String path;
    }

    private String sentence;
}
