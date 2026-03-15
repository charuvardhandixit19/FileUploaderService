package com.charu.fileuploaderservice.Scheduler;

import com.charu.fileuploaderservice.Entity.UploadedFileData;
import com.charu.fileuploaderservice.Repository.UploadedFileDataRepository;
import com.charu.fileuploaderservice.Services.FileParsingService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Component
public class FileProcessingScheduler {

    private final FileParsingService fileParsingService;
    private final UploadedFileDataRepository uploadedFileDataRepository;

    public FileProcessingScheduler(FileParsingService fileParsingService,
                                   UploadedFileDataRepository uploadedFileDataRepository) {

        this.fileParsingService = fileParsingService;
        this.uploadedFileDataRepository = uploadedFileDataRepository;
    }

    @Scheduled(fixedDelay = 20000)
    public void processFiles() {

        String processingFolder =
                "C:\\Users\\Isha Vardhan Dixit\\Desktop\\CharuVardhanDixit\\SpringBootMicroserviceProject\\FileInputFolder\\Processing";

        String processedFolder =
                "C:\\Users\\Isha Vardhan Dixit\\Desktop\\CharuVardhanDixit\\SpringBootMicroserviceProject\\FileInputFolder\\Processed";

        File folder = new File(processingFolder);

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {

            System.out.println("No files to process");
            return;
        }

        for (File file : files) {

            if (!file.isFile())
                continue;

            System.out.println("Processing file: " + file.getName());

            Optional<UploadedFileData> fileDataOptional =
                    uploadedFileDataRepository.findByFileName(file.getName());


            if (fileDataOptional.isEmpty()) {
                System.out.println("File metadata not found in DB");
                continue;
            }

            UploadedFileData fileData = fileDataOptional.get();

            boolean success = fileParsingService.parseExcelFile(file, fileData);

            if (success) {

                moveFile(file, processedFolder);

                System.out.println("File processed successfully");
            }
        }
    }

    private void moveFile(File file, String targetFolder) {

        try {

            Path source = file.toPath();

            Path target = Paths.get(targetFolder, file.getName());

            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {

            System.out.println("Error moving file: " + e.getMessage());
        }
    }
}
