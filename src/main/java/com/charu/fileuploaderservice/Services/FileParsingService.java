package com.charu.fileuploaderservice.Services;

import com.charu.fileuploaderservice.Entity.ParsedData;
import com.charu.fileuploaderservice.Entity.UploadedFileData;
import com.charu.fileuploaderservice.Enum.FileStatus;
import com.charu.fileuploaderservice.Repository.ParsedDataRepository;
import com.charu.fileuploaderservice.Repository.UploadedFileDataRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class FileParsingService {

    private final ParsedDataRepository parsedDataRepository;
    private final UploadedFileDataRepository uploadedFileDataRepository;

    public FileParsingService(ParsedDataRepository parsedDataRepository,
                              UploadedFileDataRepository uploadedFileDataRepository) {

        this.parsedDataRepository = parsedDataRepository;
        this.uploadedFileDataRepository = uploadedFileDataRepository;
    }

    public boolean parseExcelFile(File file, UploadedFileData fileData) {

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    continue; // skip header
                }

                String transactionId = row.getCell(0).getStringCellValue();

                if (parsedDataRepository.findByTransactionId(transactionId) != null) {
                    continue;
                }

                ParsedData parsedData = ParsedData.builder()
                        .transactionId(transactionId)
                        .accountNumber(row.getCell(1).getStringCellValue())
                        .customerName(row.getCell(2).getStringCellValue())
                        .amount(row.getCell(3).getNumericCellValue())
                        .currency(row.getCell(4).getStringCellValue())
                        .transactionType(row.getCell(5).getStringCellValue())
                        .status(FileStatus.COMPLETED)
                        .build();

                parsedDataRepository.save(parsedData);
            }

            fileData.setStatus(FileStatus.COMPLETED);
            uploadedFileDataRepository.save(fileData);

            return true;

        } catch (Exception e) {

            fileData.setStatus(FileStatus.FAILED);
            uploadedFileDataRepository.save(fileData);

            System.out.println("Excel parsing error: " + e.getMessage());

            return false;
        }
    }
}
