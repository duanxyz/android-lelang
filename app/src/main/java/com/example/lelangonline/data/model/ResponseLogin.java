package com.example.lelangonline.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("permissions")
	private List<String> permissions;

	@SerializedName("roles")
	private List<String> roles;

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setPermissions(List<String> permissions){
		this.permissions = permissions;
	}

	public List<String> getPermissions(){
		return permissions;
	}

	public void setRoles(List<String> roles){
		this.roles = roles;
	}

	public List<String> getRoles(){
		return roles;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLogin{" + 
			"permissions = '" + permissions + '\'' +
			",roles = '" + roles + '\'' +
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' +
			",email = '" + email + '\'' +
			",username = '" + username + '\'' + 
			"}";
		}
}