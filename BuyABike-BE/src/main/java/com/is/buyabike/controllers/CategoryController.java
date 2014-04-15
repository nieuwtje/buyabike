package com.is.buyabike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.is.buyabike.domain.Category;
import com.is.buyabike.domain.Product;
import com.is.buyabike.services.CategoryService;
import com.is.buyabike.services.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Category> getAllCategories(){	
		List<Category> categories= categoryService.getCategories();	
		return categories;
				
	}
	
	@RequestMapping(value="/withproducts",method = RequestMethod.GET)
	public @ResponseBody List<Category> getAllCategoriesWithProducts(){	
		List<Category> categories= categoryService.getCategoriesWithProducts();	
		return categories;
				
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public @ResponseBody Category getCategory(@PathVariable long id){
		return categoryService.getCategory(id);
	}
}
