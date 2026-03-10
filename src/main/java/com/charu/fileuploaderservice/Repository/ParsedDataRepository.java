package com.charu.fileuploaderservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charu.fileuploaderservice.Entity.ParsedData;
import com.charu.fileuploaderservice.Enum.FileStatus;

@Repository
public interface ParsedDataRepository extends JpaRepository<ParsedData, Long> {

    // Custom query to find by transactionId
    ParsedData findByTransactionId(String transactionId);

    // Custom query to find all records with a specific status
    List<ParsedData> findByStatus(FileStatus status);
}
