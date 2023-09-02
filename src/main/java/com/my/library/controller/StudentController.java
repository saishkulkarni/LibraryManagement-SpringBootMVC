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

import com.my.library.dto.Student;
import com.my.library.helper.LoginHelper;
import com.my.library.service.StudentService;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/signup")
	public String gotoSignup() {
		return "Signup";
	}

	@GetMapping("/login")
	public String gotoLogin() {
		return "Login";
	}

	@PostMapping("/signup")
	public String signup(Student student, @RequestParam String date, @RequestParam MultipartFile pic, ModelMap model)
			throws IOException {
		return studentService.signup(student, date, pic, model);
	}

	@GetMapping("/verify/{id}/{token}")
	public String createStudent(@PathVariable int id, @PathVariable String token, ModelMap model) {
		return studentService.createStudentAccount(id, token, model);
	}

	@PostMapping("/login")
	public String loginStudent(LoginHelper helper, ModelMap model, HttpSession session) {
		return studentService.login(helper, model, session);
	}

	@GetMapping("/viewbooks")
	public String viewBooks(HttpSession session, ModelMap map) {
		return studentService.fetchBooks(session, map);
	}

	@PostMapping("/fetchbook")
	public String fetchBook(@RequestParam String name, ModelMap model, HttpSession session) {
		return studentService.fetchBooks(name, model, session);
	}

	@GetMapping("/edit")
	public String edit(HttpSession session, ModelMap map) {
		return studentService.edit(session, map);
	}

	@PostMapping("/update")
	public String update(Student student, @RequestParam String date, ModelMap model, HttpSession session) {
		return studentService.update(student, date, model, session);
	}

	@GetMapping("/borrow/{id}")
	public String borrow(@PathVariable int id, HttpSession session, ModelMap map) {
		return studentService.borrow(id, session, map);
	}

	@GetMapping("/history")
	public String viewHistory(HttpSession session, ModelMap map) {
		return studentService.viewBorrowHistory(session, map);
	}

	@GetMapping("/return")
	public String returnBook(HttpSession session, ModelMap map) {
		return studentService.returnBook(session, map);
	}

	@GetMapping("/payfine")
	public String payFine(HttpSession session, ModelMap map) throws RazorpayException {
		return studentService.payFine(session, map);
	}

	@PostMapping("/paymentSuccess")
	public String paymentComplete(@RequestParam String razorpay_payment_id, HttpSession session, ModelMap map) {
		return studentService.paymentComplete(razorpay_payment_id,session,map);
	}
}
