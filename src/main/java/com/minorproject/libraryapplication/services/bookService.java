package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.Author;
import com.minorproject.libraryapplication.Model.Book;
import com.minorproject.libraryapplication.repositories.bookRepository;
import com.minorproject.libraryapplication.requestPOJO.bookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class bookService{

    @Autowired
    bookRepository bookRepository;

    @Autowired
    authorService authorService;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(int book_id){
        return bookRepository.findById(book_id).orElse(Book.builder().build());
    }

    public List<Book> getBookByName(String name){
        return bookRepository.findByBookName(name);
    }

    public void createBook(bookRequest bookRequest){

        String email=bookRequest.getAuthorRequest().getEmail();
        Author author=authorService.getAuthorByEmail(email);
        if(author!=null) {
            System.out.println(author.getEmail());
        }
        else{
            System.out.println("author is null");
        }


        Book book= Book.builder()
                .bookName(bookRequest.getBookName())
                .bookCategory(bookRequest.getBookCategory())
                .cost(bookRequest.getBookCost())
                .build();

        if(author==null){
            author= Author.builder()
                    .name(bookRequest.getAuthorRequest().getName())
                    .age(bookRequest.getAuthorRequest().getAge())
                    .email(email)
                    .country(bookRequest.getAuthorRequest().getCountry())
                    .books(Arrays.asList(book))
                    .build();

            authorService.createAuthor(author);
        }
//        else{
//            if(bookRepository.findByBookName(bookRequest.getBookName())==null) {
//                author.setBooks(Arrays.asList(book));
//                authorService.createAuthor(author);
//            }
//        }
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public void updateBookById(int book_id, bookRequest bookRequest) {
        Book book = bookRepository.findById(book_id).orElseThrow();
        if (bookRequest.getBookName() != null) {
            book.setBookName(bookRequest.getBookName());
        }
        if (bookRequest.getBookCost() != 0) {
            book.setCost(bookRequest.getBookCost());
        }
        if (bookRequest.getBookCategory() != null) {
            book.setBookCategory(bookRequest.getBookCategory());
        }
        bookRepository.save(book);
    }


    public String deleteBookById(int id) {
        while (true) {
            bookRepository.deleteById(id);
            return ("Successfully book deleted by \nid: " + id);
        }
    }

    public void saveOrUpdateBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBooksTakenByStudent(int id){
        return bookRepository.getAllBooksTakenByStudent(id);
    }


}
