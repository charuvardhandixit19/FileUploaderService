package com.charu.fileuploaderservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import com.charu.fileuploaderservice.Enum.FileStatus;
import java.time.LocalDateTime;

@Entity
@Table(name = "_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParsedData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String transactionType; // CREDIT / DEBIT

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private FileStatus status; // SUCCESS / FAILED
}
