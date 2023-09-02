package com.my.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.library.dto.BookRecord;
import com.my.library.repository.BookRecordRepository;

@Repository
public class BookRecordDao {

	@Autowired
	BookRecordRepository recordRepository;

	public BookRecord saveRecord(BookRecord record) {
		return recordRepository.save(record);
	}
}