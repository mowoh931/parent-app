package com.baar.parentapp.api;

import com.baar.parentapp.model.Post;
import com.baar.parentapp.model.Student;
import com.baar.parentapp.model.User;
import com.baar.parentapp.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApi {


    private final StudentService studentService;

    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/get/all", produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @GetMapping("/get/single")
    public Student getStudent() throws IOException {
        return studentService.getStudent();
    }

    @GetMapping(value = "/get/all/flux", produces = MediaType.APPLICATION_JSON_VALUE)

    public Flux<Student> getFluxStudents() {

        return studentService.getFluxStudents();
    }

    @GetMapping("get/all/users")
    public List<User> getUsers() {
        return studentService.getUsers();
    }

    @GetMapping("get/all/posts")
    public Flux<Post> getPostList() {
       return studentService.getPostList();
    }
}
