package com.dfs.spring.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.dfs.spring.web.validation.ValidEmail;

public class User {
	
	@NotBlank(message="Username cannot be blank.")
	@Size(min=8,max=15)
	@Pattern(regexp="^\\w{8,}$", message="Username can only consist of numbers, letters,and ")
	private String username;

	@NotBlank(message="Password cannot be blank.")
	@Size(min=8,max=15, message="Password must be between 8 and 15 characters long.")
	@Pattern(regexp="^\\S+$")
	private String password;
	private boolean enabled = false;
	private String authority;
	@ValidEmail(message="Not a valid email")
	private String email;

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String username, String password, boolean enabled, String authority, String email) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
