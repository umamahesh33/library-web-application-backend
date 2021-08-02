package com.minorproject.libraryapplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transactions{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnoreProperties("student")
    @JoinColumn
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("book")
    @JoinColumn
    private Book book;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String TransactionId;

    @CreationTimestamp
    private Date transactionDate;

    @Column(nullable = false)
    private int fine;
    private String remarks;

}
