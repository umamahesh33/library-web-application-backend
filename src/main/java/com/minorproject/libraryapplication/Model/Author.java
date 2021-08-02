package com.minorproject.libraryapplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Author{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Author_name",nullable = false)
    private String name;

    @Column(name = "Author_email",nullable = false,unique = true)
    private String email;

    private int age;
    private String country;

    @OneToMany
    @Transient
    @JsonIgnoreProperties("author")
    private List<Book> books;

}
