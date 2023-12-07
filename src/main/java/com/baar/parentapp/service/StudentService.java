package com.baar.parentapp.service;

import com.baar.parentapp.model.Post;
import com.baar.parentapp.model.Student;
import com.baar.parentapp.model.User;
import com.baar.parentapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private
    WebClient webClient;

    List<Student> students = List.of(new Student("One", "John", "Dallas"), new Student("Two", "Peter", "New York"));


    public List<Student> getStudents() {


        List<Student> students = List.of(new Student("One", "John", "Dallas"), new Student("Two", "Peter", "New York"));
        return students;
    }

    public Student getStudent() throws IOException {
        File file = new File(Constants.studentPath);
        FileWriter fileWriter = new FileWriter(file);

        Student student = new Student("One", "John", "Dallas");
        try {
            fileWriter.write(student.toString());
            fileWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        fileWriter.close();

        return student;
    }


    public Flux<Student> getFluxStudents() {

        return Flux.fromIterable(students).doOnComplete(() -> {
                }).log("Students ")
                .retry(2)
                .cache();

    }


    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        User[] users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", User[].class);
        assert users != null;


        return Arrays.asList(users);

    }


    public Flux<Post> getPostList() {
        return webClient.get().uri(Constants.POST_URL)
                .retrieve()
                .bodyToFlux(Post.class);
    }

    public Mono<Post> getPostById(int id) {
        return webClient.get().uri(Constants.POST_URL)
                .retrieve()
                .bodyToMono(Post.class).filter(post -> post.getId() == id);

    }
}
