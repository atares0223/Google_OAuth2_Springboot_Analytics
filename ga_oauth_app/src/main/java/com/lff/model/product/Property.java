package com.lff.model.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * it's like a setting which the seller defined for a product to specify a sku
 * 
 * @author ffliu
 *
 */
public class Property {
	private String name;
	private List<String> values;
}
