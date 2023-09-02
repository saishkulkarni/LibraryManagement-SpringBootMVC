package com.my.library.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my.library.dto.Book;
import com.my.library.dto.Librarian;
import com.my.library.helper.LoginHelper;
import com.my.library.service.LibrarianService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@GetMapping
	public String defaultPage() {
		return "LibrarianDefault";
	}

	@GetMapping("/main")
	public String mainPage() {
		return "LibrarianMain";
	}

	@PostMapping
	public String librarian(LoginHelper helper, ModelMap model) {
		return librarianService.librarianAccess(helper, model);
	}

	@GetMapping("/signup")
	public String loadSignup() {
		return "LibrarianSignup";
	}

	@PostMapping("/signup")
	public String createLibrarianAccount(Librarian librarian, ModelMap model) {
		return librarianService.createLibrarianAccount(librarian, model);
	}

	@GetMapping("/login")
	public String login() {
		return "LibrarianLogin";
	}

	@PostMapping("/signup/{id}")
	public String createLibrarianAccount(@PathVariable int id, @RequestParam int otp, ModelMap model) {
		return librarianService.createLibrarianAccount(id, otp, model);
	}

	@PostMapping("/login")
	public String librarianLogin(LoginHelper helper, ModelMap model, HttpSession session) {
		return librarianService.login(helper, model, session);
	}

	@GetMapping("/addbook")
	public String addBook() {
		return "AddBook";
	}

	@PostMapping("/addbook")
	public String addBook(Book book, @RequestParam MultipartFile pic, ModelMap model, HttpSession session)
			throws IOException {
		return librarianService.addBook(book, pic, model, session);
	}

	@GetMapping("/fetchbooks")
	public String fetchBooks(ModelMap model, HttpSession session) {
		return librarianService.fetchAllBooks(model, session);
	}

	@PostMapping("/fetchbook")
	public String fetchBook(@RequestParam String name, ModelMap model, HttpSession session) {
		return librarianService.fetchBooks(name, model, session);
	}

	@GetMapping("/book/delete/{id}")
	public String deleteBook(@PathVariable int id, ModelMap model, HttpSession session) {
		return librarianService.delete(id, model, session);
	}

	@GetMapping("/book/edit/{id}")
	public String editBook(@PathVariable int id, ModelMap map, HttpSession session) {
		return librarianService.edit(id, map, session);
	}

	@PostMapping("/updatebook")
	public String updateBook(Book book, ModelMap model, HttpSession session) throws IOException {
		return librarianService.updateBook(book, model, session);
	}
	@GetMapping("/history")
	public String viewHistory(HttpSession session, ModelMap map) {
		return librarianService.viewBorrowHistory(session, map);
	}
	
	@PostMapping("/history")
	public String viewHistory(HttpSession session, ModelMap map,@RequestParam int id) {
		return librarianService.viewBorrowHistory(session, map,id);
	}
}
