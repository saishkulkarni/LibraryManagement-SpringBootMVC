package com.my.library.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.my.library.dao.BookDao;
import com.my.library.dao.LibrarianDao;
import com.my.library.dto.Book;
import com.my.library.dto.BookRecord;
import com.my.library.dto.Librarian;
import com.my.library.helper.LoginHelper;
import com.my.library.helper.SendMailLogic;

import jakarta.servlet.http.HttpSession;

@Service
public class LibrarianService {

	@Autowired
	LibrarianDao librarianDao;

	@Autowired
	SendMailLogic mailLogic;

	@Autowired
	BookDao bookDao;

	public String librarianAccess(LoginHelper helper, ModelMap model) {
		if (helper.getEmail().equals("admin") && helper.getPassword().equals("admin")) {
			model.put("pos", "Authorization Success");
			return "LibrarianMain";
		} else {
			model.put("neg", "Unauthorized You can not access");
			return "Home";
		}
	}

	public String createLibrarianAccount(Librarian librarian, ModelMap model) {
		int otp = new Random().nextInt(100000, 999999);
		librarian.setOtp(otp);

		Librarian librarian2 = librarianDao.fetchByEmail(librarian.getEmail());

		if (librarian2 == null) {
			librarianDao.save(librarian);
			mailLogic.librarianSignupMail(librarian);
			model.put("pos", "Otp Sent Success");
			model.put("id", librarian.getId());
			return "VerifyOtp";
		} else {
			if (librarian2.isStatus()) {
				model.put("neg", "Email Should not be Repeated");
				return "LibrarianSignup";
			} else {
				mailLogic.librarianSignupMail(librarian2);
				model.put("pos", "Otp Sent Success");
				model.put("id", librarian2.getId());
				return "VerifyOtp";
			}
		}
	}

	public String createLibrarianAccount(int id, int otp, ModelMap model) {
		Librarian librarian = librarianDao.findById(id);
		if (librarian == null) {
			model.put("neg", "Something Went Wrong");
			return "LibrarianSignup";
		} else {
			if (librarian.getOtp() == otp) {
				librarian.setStatus(true);
				librarianDao.save(librarian);
				model.put("pos", "Account Created Successfully");
				return "LibrarianLogin";

			} else {
				model.put("neg", "OTP MissMatch");
				model.put("id", librarian.getId());
				return "VerifyOtp";
			}
		}
	}

	public String login(LoginHelper helper, ModelMap model, HttpSession session) {
		Librarian librarian = librarianDao.fetchByEmail(helper.getEmail());
		if (librarian == null) {
			model.put("neg", "Invalid Email");
			return "LibrarianLogin";
		} else {
			if (librarian.getPassword().equals(helper.getPassword())) {
				if (librarian.isStatus()) {
					session.setMaxInactiveInterval(60);
					session.setAttribute("librarian", librarian);
					model.put("pos", "Login Success");
					return "LibrarianHome";
				} else {
					model.put("neg", "OTP Verification Pending");
					return "LibrarianLogin";
				}
			} else {
				model.put("neg", "Invalid Password");
				return "LibrarianLogin";
			}
		}
	}

	public String addBook(Book book, MultipartFile pic, ModelMap model, HttpSession session) throws IOException {
		if (session.getAttribute("librarian") == null) {
			model.put("neg", "Invalid Session");
			return "Home";
		} else {
			byte[] picture = new byte[pic.getInputStream().available()];
			pic.getInputStream().read(picture);
			book.setPicture(picture);

			Book book2 = bookDao.findbyNameAndAuthor(book.getName(), book.getAuthor());
			if (book2 == null) {
				book.setStatus(true);
				bookDao.save(book);
				model.put("pos", "Book Added Successfully");
				return "LibrarianHome";
			} else {
				book2.setStatus(true);
				book2.setQuantity(book2.getQuantity() + book.getQuantity());
				bookDao.save(book2);
				model.put("pos", "Book Added Successfully");
				return "LibrarianHome";
			}
		}
	}

	public String fetchAllBooks(ModelMap model, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			model.put("neg", "Invalid Session");
			return "Home";
		} else {
			List<Book> books = bookDao.findAll();
			if (books.isEmpty()) {
				model.put("neg", "No Books Found");
				return "LibrarianHome";
			} else {
				model.put("books", books);
				return "LibrarianBooks";
			}
		}
	}

	public String fetchBooks(String name, ModelMap model, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			model.put("neg", "Invalid Session");
			return "Home";
		} else {
			if (name.equals("")) {
				return fetchAllBooks(model, session);
			} else {
				List<Book> books = bookDao.findByName(name);
				if (books.isEmpty())
					books = bookDao.findByAuthor(name);

				if (books.isEmpty())
					model.put("neg", "Book Not Found");

				model.put("books", books);
				return "LibrarianBooks";
			}
		}
	}

	public String delete(int id, ModelMap model, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			model.put("neg", "Invalid Session");
			return "Home";
		} else {
			Book book = bookDao.findById(id);
			if (book == null) {
				model.put("neg", "Book Not Found");
				return fetchAllBooks(model, session);
			} else {
				bookDao.deleteBook(id);
				model.put("pos", "Book Deleted Successfully");
				return fetchAllBooks(model, session);
			}
		}

	}

	public String edit(int id, ModelMap map, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			map.put("neg", "Invalid Session");
			return "Home";
		} else {
			Book book = bookDao.findById(id);
			if (book == null) {
				map.put("neg", "Book Not Found");
				return fetchAllBooks(map, session);
			} else {
				map.put("book", book);
				return "EditBook";
			}
		}
	}

	public String updateBook(Book book, ModelMap model, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			model.put("neg", "Invalid Session");
			return "Home";
		} else {
			Book book2 = bookDao.findById(book.getId());
			book.setPicture(book2.getPicture());
			book.setStatus(book2.isStatus());
			bookDao.save(book);
			model.put("pos", "Book Updated Successfully");
			return "LibrarianHome";
		}
	}

	public String viewBorrowHistory(HttpSession session, ModelMap map) {
		if (session.getAttribute("librarian") == null) {
			map.put("neg", "Invalid Session");
			return "Home";
		} else {
			return "BookId";
		}
	}

	public String viewBorrowHistory(HttpSession session, ModelMap map, int id) {
		if (session.getAttribute("librarian") == null) {
			map.put("neg", "Invalid Session");
			return "Home";
		} else {
			Book book = bookDao.findById(id);
			if (book == null) {
				map.put("neg", "Book Not Found");
				return "LibrarianHome";
			}
			else {
				List<BookRecord> records=book.getRecords();
				if(records.isEmpty())
				{
					map.put("neg", "No History Found");
					return "LibrarianHome";
				}
				else {
					map.put("records", records);
					return "BookHistory";
				}
			}
		}
	}
	
	
}
