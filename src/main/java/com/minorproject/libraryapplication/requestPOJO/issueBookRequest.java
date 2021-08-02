package com.minorproject.libraryapplication.requestPOJO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class issueBookRequest{
    private int book_id;

}
