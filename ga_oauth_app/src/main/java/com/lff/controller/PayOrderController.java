package com.lff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lff.model.placeorder.PayOrderVO;
import com.lff.model.response.AppResponse;
import com.lff.model.response.AppResponse.AppResponseStatusCodeEnum;

@Controller
@RequestMapping("/pay")
public class PayOrderController {
	@RequestMapping("")
	@ResponseBody
	public AppResponse listSkus(PayOrderVO placeOrder) {
		System.out.println(placeOrder);
		return new AppResponse(AppResponseStatusCodeEnum.Sucess.getStatusCode(),null);
	}
}
