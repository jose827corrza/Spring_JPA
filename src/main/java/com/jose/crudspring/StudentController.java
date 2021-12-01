package com.jose.crudspring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/students-crud", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    @Autowired
    private StudentRepository stdRepo;

    @GetMapping(value = "/students")
    public List<Student> getStudents(){
        return stdRepo.findAll();
    }
    @GetMapping(value = "students/{id}")
    Student getStudent(@PathVariable Long id){
        return stdRepo.findById(id).get();
    }

    @PostMapping("/newStudent")
    Student postStudent(@RequestBody Student std){
        return stdRepo.save(std);
    }

    @PutMapping("/modStudent/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id){
        return stdRepo.findById(id).map(student -> {
            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            student.setAge(newStudent.getAge());
            student.setGender(newStudent.getGender());
            return stdRepo.save(student);
        }).orElseGet(() -> {
            newStudent.setId(id);
            return stdRepo.save(newStudent);
        });
    }

    @DeleteMapping("/deleteStudent/{id}")
    void deleteStudent(@PathVariable Long id){
        stdRepo.deleteById(id);
    }

}
