package com.lff.model;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user" )
public class User {
	@AutoID
	@UpdateIgnore
	private Integer id;
	@NonNull
	private String name;
	private String age;
	private String gender;
	@NonNull
	private String email;
	private String phone;
	private String[] interests;
	@NonNull
	private String password;
	@UpdateIgnore
	private Date createAt;
	private Date updateAt;
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
}
