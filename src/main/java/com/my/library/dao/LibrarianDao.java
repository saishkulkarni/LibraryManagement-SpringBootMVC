package com.my.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.library.dto.Librarian;
import com.my.library.repository.LibrarianRepository;

@Repository
public class LibrarianDao {

    @Autowired
    LibrarianRepository librarianRepository;

    public Librarian fetchByEmail(String email) {
        return librarianRepository.findByEmail(email);
    }

    public void save(Librarian librarian) {
        librarianRepository.save(librarian);
    }

    public Librarian findById(int id) {
       return librarianRepository.findById(id).orElse(null);
    }

}
