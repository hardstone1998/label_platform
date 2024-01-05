package com.ruoyi.task.controller;

import com.ruoyi.task.service.IASR2ChannelService;
import com.ruoyi.task.service.IQA2ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author：乔石磊
 * @date：10:49 2024/1/4
 */
@Slf4j
@RestController
@RequestMapping("/audioUpload")
public class Audio2ChannelController {

    @Value("${label.update.directory}")
    private String  UPLOAD_DIR ;

    @Autowired
    private IQA2ChannelService qa2ChannelService;

    @Autowired
    private IASR2ChannelService asr2ChannelService;

    private static final int BUFFER_SIZE = 1024;
    @PostMapping("/asr")
    public ResponseEntity<String> handleASRUpload(@RequestParam("file") MultipartFile file) {
        log.info("ASR上传文件");
        return handleFileUpload(file,"asr");
    }
    @PostMapping("/qa")
    public ResponseEntity<String> handleQAUpload(@RequestParam("file") MultipartFile file) {
        log.info("QA上传文件");
        return handleFileUpload(file,"qa");
    }

//    存储到文件夹中
    private ResponseEntity<String> handleFileUpload(MultipartFile file,String clazz) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            String fileName = file.getOriginalFilename();
            Objects.requireNonNull(fileName);

            String folderName = clazz+UPLOAD_DIR;
            Path folderPath = Paths.get(folderName);

            if (!Files.exists(folderPath)) {
                // 如果不存在，创建文件夹
                try {
                    Files.createDirectory(folderPath);
                    System.out.println("文件夹已创建");
                } catch (IOException e) {
                    System.err.println("无法创建文件夹: " + e.getMessage());
                }
            }

            // 构建目标文件路径
            Path targetPath = Paths.get(folderName, fileName);

            // 处理zip文件
            if (fileName.endsWith(".zip")) {
                handleZipFile(file, targetPath);
//                处理zip包
                if("qa".equals(clazz)) {

                }else if ("asr".equals(clazz)){

                }

            } else {
                // 直接保存其他类型文件
                saveFile(file, targetPath);

//                直接处理音频文件
                if("qa".equals(clazz)) {
                    qa2ChannelService.handleAudioFile(targetPath);
                }else if ("asr".equals(clazz)){
                    asr2ChannelService.handleAudioFile(targetPath);
                }
            }

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while uploading file");
        }
    }

    private void saveFile(MultipartFile file, Path targetPath) throws IOException {
        System.out.println(targetPath);
        // 直接保存文件
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

//    处理zip文件
    private void handleZipFile(MultipartFile zipFile, Path targetPath) throws IOException {
        // 创建目标文件夹
        Files.createDirectories(targetPath);

        // 解压zip文件
        try (ZipInputStream zipInputStream = new ZipInputStream(zipFile.getInputStream())) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path entryPath = targetPath.resolve(entry.getName());
                createDirectories(entryPath);

                if (!entry.isDirectory()) {
                    extractFile(zipInputStream, entryPath);
                }
            }
        }
    }

    private static void createDirectories(Path path) throws IOException {
        // 创建目录，包括父目录
        Files.createDirectories(path.getParent());
    }
    private static void extractFile(ZipInputStream zipInputStream, Path entryPath) throws IOException {
        // 处理文件内容的复制
        try (BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(entryPath))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }

}
