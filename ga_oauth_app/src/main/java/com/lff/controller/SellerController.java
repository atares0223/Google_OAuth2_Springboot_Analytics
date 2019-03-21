package com.lff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mock.seller.MockSeller;

@Controller
@RequestMapping("/seller")
public class SellerController {
	  @RequestMapping("/list")
	    public String  listSellers(Model model) {
	        model.addAttribute("sellers", MockSeller.getMockSellers());
	        return "/seller/list";
	    }
}
