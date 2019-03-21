package com.lff.vo;

import com.lff.convertor.NomalConverter;
import com.lff.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO implements NomalConverter<User>{
	@NonNull
	private String name ;
	@NonNull
	private String email;
	@NonNull
	private String password;
	
	@Override
	public User convert() {
		User user = new User(name, email, password);
		return user;
	}
}
