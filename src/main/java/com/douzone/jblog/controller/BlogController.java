package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"/{id}"})
	public String main(@PathVariable("id") String id,
//			@PathVariable("categoryNo") Optional<Long> categoryNo,
//			@PathVariable("postNo") Optional<Long> postNo,
			Model model) {
		model.addAttribute("map",blogService.getBlog(id));
		/*
		 * if(categoryNo.isPresent()) {
		 * model.addAttribute("categoryNo",categoryNo.get());
		 * 
		 * }else { model.addAttribute("categoryNo",0); }
		 */
		return "blog/blog-main";
	}
	
}
