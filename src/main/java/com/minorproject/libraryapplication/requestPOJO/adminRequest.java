package com.minorproject.libraryapplication.requestPOJO;

import com.minorproject.libraryapplication.Model.applicationUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class adminRequest{

    private String name;
    private int age;
    private String email;
    private userRequest userRequest;

}
