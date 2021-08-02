package com.minorproject.libraryapplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Student_name",nullable = false)
    private String name;

    @Column(name = "Student_age",nullable = false)
    private int age;

    @Column(name = "email_id",nullable = false,unique = true)
    private String email;

    private int booksCount;

    @OneToMany
    @Transient
    private List<Book> books;

    @OneToMany
    @JsonIgnoreProperties("student")
    @Transient
    private List<Transactions> transactionsList;

    @CreationTimestamp
    private Date createdOn;

    @OneToOne
    @JsonIgnoreProperties("student")
    @JoinColumn
    private applicationUser user;

}
