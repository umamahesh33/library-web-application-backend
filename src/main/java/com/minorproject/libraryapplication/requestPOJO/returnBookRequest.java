package com.minorproject.libraryapplication.requestPOJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class returnBookRequest{

    private int book_id;
    private String remarks;
}
