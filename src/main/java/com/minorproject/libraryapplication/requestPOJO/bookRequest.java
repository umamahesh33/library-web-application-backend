package com.minorproject.libraryapplication.requestPOJO;


import com.minorproject.libraryapplication.Model.Author;
import com.minorproject.libraryapplication.Model.BookCategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class bookRequest{
    private String bookName;
    private BookCategory bookCategory;
    private int bookCost;
    private authorRequest authorRequest;
}
