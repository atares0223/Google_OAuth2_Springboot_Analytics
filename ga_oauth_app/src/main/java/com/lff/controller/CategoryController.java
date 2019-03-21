package com.lff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import mock.category.MockCategory;

@Controller
@RequestMapping("/category")
public class CategoryController {
	  @RequestMapping("/{sellerName}")
	    public String  listCategories(@PathVariable(value = "sellerName") String sellerName,Model model) {
	        model.addAttribute("categories", MockCategory.getCategory(sellerName));
	        return "/category/list";
	    }
	 
}
