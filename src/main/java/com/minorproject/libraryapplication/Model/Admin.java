package com.minorproject.libraryapplication.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;
    private int age;

    @Column(unique = true,nullable = false)
    private String email;

    @OneToOne
    @JsonIgnoreProperties("admin")
    private applicationUser user;
}
