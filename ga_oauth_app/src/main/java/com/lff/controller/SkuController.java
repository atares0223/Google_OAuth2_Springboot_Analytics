package com.lff.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lff.model.product.Property;
import com.lff.model.product.Sku;

import mock.product.MockApples;
import mock.product.property.MockGenevaEarlyProperty;
import mock.product.property.MockJerseyMacProperty;
import mock.product.property.MockRoyalGalaProperty;
import mock.sku.MockGenevaEarlySku;
import mock.sku.MockJerseyMacSku;
import mock.sku.MockRoyalGalaSku;

@Controller
@RequestMapping("/sku")
public class SkuController {
	@RequestMapping("/{productName}")
	public String listSkus(@PathVariable(value = "productName") String productName, Model model) {
		List<Sku> skus = null;
		List<Property> properties = null;
		if (productName.equals(MockApples.GENEVA_EARLY_APPLE.getName())) {
			skus = MockGenevaEarlySku.getMockGenevaEarlySku();
			properties = MockGenevaEarlyProperty.getMockGenevaEarlyProperty();
		} else if (productName.equals(MockApples.JERSEYMAC_APPLE.getName())) {
			skus = MockJerseyMacSku.getMockJerseyMacSku();
			properties = MockJerseyMacProperty.getMockJerseyMacProperty();
		} else if (productName.equals(MockApples.ROYAL_GALA_APPLE.getName())) {
			skus = MockRoyalGalaSku.getMockRoyalGalaSku();
			properties = MockRoyalGalaProperty.getMockRoyalGalaProperty();
		}
		model.addAttribute("skus", skus);
		model.addAttribute("properties", properties);
		return "/sku/list";
	}

}
