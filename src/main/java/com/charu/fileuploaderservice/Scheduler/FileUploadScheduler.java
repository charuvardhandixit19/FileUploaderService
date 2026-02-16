package com.charu.fileuploaderservice.Scheduler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FileUploadScheduler {
     // To print logs....
    private static final Logger logger = LoggerFactory.getLogger(FileUploadScheduler.class);
    // function to pick files from input folder....
    @Scheduled(fixedDelay = 30000)
    public void scanInputFolder() {
        logger.info("Scheduler started scanning input folder");
        String inputFolderPath = "C:\\Users\\Isha Vardhan Dixit\\Desktop\\CharuVardhanDixit\\SpringBootMicroserviceProject\\FileInputFolder\\Input";
        String processingFolderPath="C:\\Users\\Isha Vardhan Dixit\\Desktop\\CharuVardhanDixit\\SpringBootMicroserviceProject\\FileInputFolder\\Processing";
        //  Create folder Object....
        File folder = new File(inputFolderPath);
        if(!folder.exists() || !folder.isDirectory()){
            logger.info("Folder path does not exist");
        }
        // File object array to store all file
        File[] files = folder.listFiles();
        if(files==null || files.length==0){
            logger.info("No file to process");
            return;
        }
        // 
        for(File file:files){
            if(file.isFile()){
                logger.info("The name of File is" + file.getName());
                moveFile(file, processingFolderPath);
            }
        }


    }

    // Function to move file Location from one place to another.
    public void moveFile(File file,String targetFolder){
        try{
            Path sourcePath = file.toPath();
            Path targetPath = Paths.get(targetFolder, file.getName());
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File moved successfully");
        }catch(Exception e){
            logger.info(e.getMessage());
        }
    }

}
