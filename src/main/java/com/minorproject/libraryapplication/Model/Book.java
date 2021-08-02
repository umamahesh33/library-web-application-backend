package com.minorproject.libraryapplication.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Books")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Book_Id")
    private int id;

    @Column(name = "book_name",nullable = false)
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;

    @Column(name = "cost",nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany
    @Transient
    @JsonIgnoreProperties("book")
    private List<Transactions> transactionsList;

    @CreationTimestamp
    private Date createdOn;


}
