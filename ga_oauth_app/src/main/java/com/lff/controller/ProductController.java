package com.lff.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lff.model.product.Product;

import mock.category.MockCategory;
import mock.product.MockApples;

@Controller
@RequestMapping("/product")
public class ProductController {
	  @RequestMapping("/{categoryName}")
	    public String  listProducts(@PathVariable(value = "categoryName") String categoryName,Model model) {
		  	List<Product> products = null;
		  	if(categoryName.equals(MockCategory.APPLE)){
		  		products = MockApples.getMockApples();
		  	}
	        model.addAttribute("products", products);
	        return "/product/list";
	    }
	 
}
