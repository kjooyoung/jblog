package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class AdminService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
	}
	
	public BlogVo getBlogInfo(Long no) {
		return blogDao.getByNo(no);
	}
	
	public List<CategoryVo> getListByNo(Long no){
		return categoryDao.getListByNo(no);
	}
	
	public void insertCategory(CategoryVo categoryVo) {
		categoryDao.insert(categoryVo);
	}
	
	public void insertPost(PostVo postVo) {
		postDao.insert(postVo);
	}
}
