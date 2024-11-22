package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;



@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBook();
//		ModelAndView mav = ModelAndView();
//		mav.setViewName("bookList");
//		mav.addObject("book",list);
		return new ModelAndView("bookList","books",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
	    bookService.save(b);
	    return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMybook(Model model) {
		
		List<MyBookList> list = myBookService.getAllBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@GetMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b = bookService.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBook(mb);
		return "redirect:/my_books";
	}
	
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book b = bookService.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/available_books";
	}
	
	
	
}