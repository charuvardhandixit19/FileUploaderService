package com.charu.fileuploaderservice.Scheduler;

import com.charu.fileuploaderservice.Entity.UploadedFileData;
import com.charu.fileuploaderservice.Enum.FileStatus;
import com.charu.fileuploaderservice.Repository.UploadedFileDataRepository;
import com.charu.fileuploaderservice.Services.FileService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileProcessingScheduler {

    // private final UploadedFileDataRepository repository;
    // private final FileService processingService;

    // public FileProcessingScheduler(UploadedFileRepository repository,
    //                                FileProcessingService processingService) {

    //     this.repository = repository;
    //     this.processingService = processingService;
    // }

    // @Scheduled(fixedDelay = 20000)
    // public void processFiles() {

    //     System.out.println("Processing scheduler started...");

    //     List<UploadedFileData> files =
    //             repository.findByStatus(FileStatus.RECEIVED);

    //     if (files.isEmpty()) {

    //         System.out.println("No files to process");
    //         return;
    //     }

    //     for (UploadedFileData file : files) {

    //         processingService.processFile(file);

    //     }
    // }
}
