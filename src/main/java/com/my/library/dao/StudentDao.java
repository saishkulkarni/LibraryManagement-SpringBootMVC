package com.my.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.library.dto.Student;
import com.my.library.repository.StudentRepository;

@Repository
public class StudentDao {

    @Autowired
    StudentRepository studentRepository;

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student findByMobile(long mobile) {
        return studentRepository.findByMobile(mobile);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(int id) {
       return studentRepository.findById(id).orElse(null);
    }

}
