package com.lff.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  the category of product
 * @author ffliu
 *
 */
@Data
@AllArgsConstructor
public class Category {
	private Category parentCategory;
	private String name;
}
