package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id}/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping(value=("/basic"), method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {
		model.addAttribute("blog", adminService.getBlogInfo(id));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value={"/basic"}, method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id,
						@ModelAttribute BlogVo blogVo, HttpSession session, 
						@RequestParam("logo-file") MultipartFile multipartFile ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(id.equals(authUser.getId()) == false) {
			//접근제어
			return "redirect:/"+id;
		}
		
		blogVo.setUserNo(authUser.getNo());
		blogVo.setLogo(fileuploadService.restore(multipartFile));
		System.out.println(blogVo);
		adminService.update(blogVo);
		return "redirect:/"+authUser.getId();
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, Model model) {
		model.addAttribute("id",id);
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping(value="category/list", method=RequestMethod.GET)
	public JSONResult listCategory(@PathVariable("id") String id) {
		return JSONResult.success(adminService.getList(id));
	}
	
	@ResponseBody
	@RequestMapping(value="category", method=RequestMethod.POST)
	public JSONResult addCategory(@ModelAttribute CategoryVo categoryVo, 
							@PathVariable("id") String id, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		categoryVo.setUserNo(authUser.getNo());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("length", adminService.insertCategory(categoryVo, id));
		data.put("vo", categoryVo);
		data.putAll(data);
		return JSONResult.success(data);
	}
	
	@ResponseBody
	@RequestMapping("/category/{no}")
	public JSONResult deleteCategory(@PathVariable("no") Long no, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		return JSONResult.success(adminService.deleteCategory(no));
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		model.addAttribute("categoryList", adminService.getList(id));
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, @PathVariable("id") String id,
			@ModelAttribute PostVo postVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(id.equals(authUser.getId()) == false) {
			//접근제어
			return "redirect:/"+id;
		}
		
		adminService.insertPost(postVo);
		return "redirect:/"+authUser.getId();
	}
}
