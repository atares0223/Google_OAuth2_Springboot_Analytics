package com.lff.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppResponse {
	private Integer statusCode ;
	private String msg;
	
	public static enum AppResponseStatusCodeEnum{
		Not_Found(404),Error(500),Sucess(200);
		private Integer statusCode;
		
		public Integer getStatusCode() {
			return statusCode;
		}

		private AppResponseStatusCodeEnum(Integer statusCode) {
			this.statusCode = statusCode;
		}
		
	}
}
