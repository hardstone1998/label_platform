package com.ruoyi.task.service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author：乔石磊
 * @date：14:55 2024/1/4
 */
public interface IASR2ChannelService {

    public void handleAudioFile(Path filePath) throws IOException;
}
