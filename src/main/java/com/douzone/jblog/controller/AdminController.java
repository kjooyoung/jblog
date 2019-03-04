package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping(value=("{no}"), method=RequestMethod.GET)
	public String adminBasic(@PathVariable("no") Long no, Model model) {
		model.addAttribute("blog", adminService.getBlogInfo(no));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value={"/basic"}, method=RequestMethod.POST)
	public String adminBasic(@ModelAttribute BlogVo blogVo, HttpSession session, 
						@RequestParam("logo-file") MultipartFile multipartFile ) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		blogVo.setUserNo(userVo.getNo());
		blogVo.setLogo(fileuploadService.restore(multipartFile));
		System.out.println(blogVo);
		adminService.update(blogVo);
		return "redirect:/"+userVo.getId();
	}
	
	@RequestMapping(value="/category/{no}", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("no") Long no, Model model) {
		model.addAttribute("categoryList", adminService.getListByNo(no));
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/category/{no}", method=RequestMethod.POST)
	public String adminCategory(@ModelAttribute CategoryVo categoryVo, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		categoryVo.setNo(authUser.getNo());
		adminService.insertCategory(categoryVo);
		return "redirect:/admin/category/"+authUser.getNo();
	}
	
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String write(@PathVariable("no") Long no, Model model) {
		model.addAttribute("categoryList", adminService.getListByNo(no));
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session,
			@ModelAttribute PostVo postVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		adminService.insertPost(postVo);
		return "redirect:/"+authUser.getId();
	}
}
