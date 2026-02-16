package com.charu.fileuploaderservice.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.charu.fileuploaderservice.Enum.FileStatus;
import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_file_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadedFileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String filePath;
    @Enumerated(EnumType.STRING)
    private FileStatus status;
    private LocalDateTime uploadTime;

// NOT REQUIRED AS I AM USING LOMBOK ANNOTATIONS TO GENERATE CONSTRUCTORS AND GETTERS/SETTERS....
    // public UploadedFileData() {
    // }
    // public UploadedFileData(String filename, String filePath, String status, LocalDateTime uploadTime, Long id){
    //     this.fileName=filename;
    //     this.filePath=filePath;
    //     this.status=status;
    //     this.uploadTime=uploadTime;
    //     this.id=id;
    // }
}
