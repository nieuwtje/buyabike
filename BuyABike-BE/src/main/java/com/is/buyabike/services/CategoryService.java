package com.is.buyabike.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is.buyabike.dao.CategoryDao;
import com.is.buyabike.domain.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getCategoriesWithProducts(){
		return categoryDao.findAllWithProducts();
	}
	
	public List<Category> getCategories(){
		return categoryDao.findAll();
	}
	
	public Category getCategory(long id){
		return categoryDao.findWithId(id);
	}

}
