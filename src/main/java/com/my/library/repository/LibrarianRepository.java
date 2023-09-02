package com.my.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.library.dto.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian,Integer>
{

    Librarian findByEmail(String email);
    
}
