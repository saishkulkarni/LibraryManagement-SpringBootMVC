package com.my.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.library.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);

    Student findByMobile(long mobile);

}
