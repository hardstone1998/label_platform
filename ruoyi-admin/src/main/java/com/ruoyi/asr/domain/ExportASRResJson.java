package com.ruoyi.asr.domain;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JSONType(orders = {"audio"})
public class ExportASRResJson {

    private Audio audio;

    @AllArgsConstructor
    @Data
    @JSONType(orders = {"audio","sentence"})
    public static class Audio{
        private String path;

        private String sentence;
    }
}
