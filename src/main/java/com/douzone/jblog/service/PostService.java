package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostDao;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
}
