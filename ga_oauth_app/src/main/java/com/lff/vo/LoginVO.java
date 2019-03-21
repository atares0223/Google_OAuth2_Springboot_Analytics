package com.lff.vo;

import org.springframework.lang.NonNull;

import com.lff.convertor.NomalConverter;
import com.lff.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO implements NomalConverter<User>{
	@NonNull
	private String email ;
	@NonNull
	private String password;
	
	@Override
	public User convert() {
		
		User user = new User(email, password);
		return user;
	}
}
