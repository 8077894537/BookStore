package com.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookStore.service.MyBookService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBookService service;
	
	@GetMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		service.deleteById(id);
		
		return "redirect:/my_books";
	}
}
