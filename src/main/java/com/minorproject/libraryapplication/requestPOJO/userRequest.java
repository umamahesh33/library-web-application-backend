package com.minorproject.libraryapplication.requestPOJO;


import lombok.*;

import javax.persistence.Access;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class userRequest{
    private String username;
    private String password;
}
