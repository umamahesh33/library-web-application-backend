package com.minorproject.libraryapplication.services;

import com.minorproject.libraryapplication.Model.Book;
import com.minorproject.libraryapplication.Model.Student;
import com.minorproject.libraryapplication.Model.applicationUser;
import com.minorproject.libraryapplication.repositories.studentRepository;
import com.minorproject.libraryapplication.repositories.userRepository;
import com.minorproject.libraryapplication.requestPOJO.studentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService{

    @Autowired
    bookService bookService;

    @Autowired
    studentRepository studentRepository;

    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse(Student.builder().build());
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public List<Book> getBooksTakenByStudent(int id){
        return bookService.getAllBooksTakenByStudent(id);
    }

    public void createStudent(studentRequest studentRequest) throws Exception {

        try {
            applicationUser user = applicationUser.builder()
                    .username(studentRequest.getUser().getUsername())
                    .password(passwordEncoder.encode(studentRequest.getUser().getPassword()))
                    .authorities("STD")
                    .build();
            user=userRepository.save(user);

            Student student = Student.builder()
                    .name(studentRequest.getName())
                    .age(studentRequest.getAge())
                    .email(studentRequest.getEmail())
                    .user(user)
                    .build();
            student=studentRepository.save(student);

//            user.setStudent(student);
//            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage() +" username already taken" );
        }
    }

    public void updateStudentById(int id,studentRequest studentRequest){
        Student student=studentRepository.getById(id);

        if(studentRequest.getName()!=null){
            student.setName(studentRequest.getName());
        }

        if(studentRequest.getAge()!=0){
            student.setAge(studentRequest.getAge());
        }

        if(studentRequest.getEmail()!=null){
            student.setEmail(studentRequest.getEmail());
        }
        studentRepository.save(student);
    }


    public String deleteStudent(int id){
        while(true) {
            studentRepository.deleteById(id);
            return "deleted successfully!";
        }
    }

    public void updateOrSaveStudent(Student student){
        studentRepository.save(student);
    }
}
