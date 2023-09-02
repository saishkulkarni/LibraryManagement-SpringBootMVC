package com.my.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.library.dto.BookRecord;

public interface BookRecordRepository extends JpaRepository<BookRecord, Integer> {

}
