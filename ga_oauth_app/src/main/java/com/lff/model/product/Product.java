package com.lff.model.product;

import com.lff.model.seller.Seller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
	private Category category;
	private Seller seller;
	private String name;
	private Double defaultPrice;// a price for the default sku
	private Integer stock;
}
