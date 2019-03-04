package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public Map<String, Object> getBlog(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blog", blogDao.getById(id));
		map.put("postList", postDao.getList(id));
		map.put("categoryList", categoryDao.getList(id));
		return map;
	}
	
}
