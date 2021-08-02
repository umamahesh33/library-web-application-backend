package com.minorproject.libraryapplication.requestPOJO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class studentRequest{

    private String name;
    private  int age;
    private String email;
    private userRequest user;

}
