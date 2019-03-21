package com.lff.model.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * it should be defined by seller
 * @author ffliu
 *
 */
public class Sku {
	private Product product;
	private List<SkuInfo> skuInfos;
	private Double price; 
}
