package com.my.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.library.dto.Book;

public interface BookRepository extends JpaRepository<Book, Integer>
{
	Book findByNameAndAuthor(String name, String author);

	List<Book> findByName(String name);

	List<Book> findByAuthor(String author);

	List<Book> findByStatus(boolean b);
}
