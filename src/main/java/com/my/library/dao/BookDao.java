package com.my.library.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Repository;

import com.my.library.dto.Book;
import com.my.library.repository.BookRepository;

@Repository
public class BookDao {

	@Autowired
	BookRepository bookRepository;
	
	public Book findbyNameAndAuthor(String name, String author) {
		return bookRepository.findByNameAndAuthor(name, author);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Book findById(int id) {
		return bookRepository.findById(id).orElse(null);
	}

	public List<Book> findByName(String name) {
		return bookRepository.findByName(name);
	}

	public List<Book> findByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findAllAvailable(boolean b) {
		return bookRepository.findByStatus(b);
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}
	
}
