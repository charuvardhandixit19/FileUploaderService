package com.charu.fileuploaderservice.Services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charu.fileuploaderservice.Entity.UploadedFileData;
import com.charu.fileuploaderservice.Enum.FileStatus;
import com.charu.fileuploaderservice.Repository.UploadedFileDataRepository;

@Service
public class FileService {

    @Autowired
    private UploadedFileDataRepository fileDatarepository;

    public void registerFile(String fileName, String folderPath) {

        UploadedFileData file = UploadedFileData.builder()
                .fileName(fileName)
                .filePath(folderPath + "\\" + fileName)
                .status(FileStatus.PROCESSING)
                .uploadTime(LocalDateTime.now())
                .build();

        fileDatarepository.save(file);
    }
}
