package com.baar.parentapp;

import com.baar.parentapp.model.Student;
import com.baar.parentapp.model.User;
import com.baar.parentapp.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ParentAppApplication {

    public ParentAppApplication() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ParentAppApplication.class, args);
        FileReader fileReader = new FileReader(new File(Constants.studentPath));
        String student;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            student = bufferedReader.readLine();
        }
        System.out.println(student);

        Gson gson = new Gson();
        String studentString = "{" + student + "}";
        Student jsonStudent = gson.fromJson(studentString, Student.class);
        System.out.println(jsonStudent);

        String fileContent = new String(Files.readAllBytes(Paths.get(Constants.studentPath)));
        ObjectMapper objectMapper = new ObjectMapper();


        File userFile = new File(String.valueOf(Path.of(Constants.users)));
        List<User> users = objectMapper.readValue(userFile, new TypeReference<List<User>>() {
        });
        for (User user : users) {
            System.out.println(user.getAddress());

        }
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().lastName());


        List<User>userList = new ArrayList<User>();


    }


}
