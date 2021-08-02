package com.minorproject.libraryapplication.repositories;

import com.minorproject.libraryapplication.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bookRepository extends JpaRepository<Book,Integer>{

    List<Book> findByBookName(String bookName);

    @Query(value = "select * from books where student_id=?1",nativeQuery = true)
    List<Book> getAllBooksTakenByStudent(int id);

}
