package com.example.lelangonline.models.users;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	@ColumnInfo(name = "email_verified_at")
	private String emailVerifiedAt;

	@SerializedName("id")
	@ColumnInfo(name = "id")
	private int id;

	@SerializedName("email")
	@ColumnInfo(name = "email")
	private String email;

	@SerializedName("username")
	@ColumnInfo(name = "username")
	private String username;

	protected User(Parcel in) {
		updatedAt = in.readString();
		createdAt = in.readString();
		emailVerifiedAt = in.readString();
		id = in.readInt();
		email = in.readString();
		username = in.readString();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(String emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public String getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"User{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(updatedAt);
		dest.writeString(createdAt);
		dest.writeString(emailVerifiedAt);
		dest.writeInt(id);
		dest.writeString(email);
		dest.writeString(username);
	}
}