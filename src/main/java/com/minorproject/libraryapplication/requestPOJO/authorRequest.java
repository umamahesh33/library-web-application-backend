package com.minorproject.libraryapplication.requestPOJO;

import lombok.*;

import java.security.SecureRandom;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class authorRequest{
    private String name;
    private int age;
    private String email;
    private String country;
}
