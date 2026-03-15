package com.charu.fileuploaderservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charu.fileuploaderservice.Entity.UploadedFileData;
import com.charu.fileuploaderservice.Enum.FileStatus;

@Repository
public interface UploadedFileDataRepository 
        extends JpaRepository<UploadedFileData, Long> {

    // Fetch files based on status
    List<UploadedFileData> findByStatus(FileStatus status);
    Optional<UploadedFileData> findByFileName(String fileName);

}
