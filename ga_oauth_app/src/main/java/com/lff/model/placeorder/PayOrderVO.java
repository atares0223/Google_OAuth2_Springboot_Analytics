package com.lff.model.placeorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderVO {
	private String size;
	private String quality;
	private String quantity;
	private String address;
	private String delivery;
	
}
