package com.example.lelangonline.models.users;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lelangonline.models.users.Converter.SaldoConverter;
import com.example.lelangonline.utils.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Constants.USER_TABLE_NAME)
//@TypeConverters(SaldoConverter.class)
public class DataItem implements Parcelable {

	public DataItem(){}

	@SerializedName("name")
	@ColumnInfo(name = "name")
	private String name;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("phone_number")
	@ColumnInfo(name = "phone_number")
	private String phoneNumber;

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	@ColumnInfo(name = "avatar")
	private String avatar;

	@SerializedName("status")
	@ColumnInfo(name = "status")
	private String status;

	@SerializedName("user_id")
	@ColumnInfo(name = "user_id")
	private int userId;

	@SerializedName("username")
	@ColumnInfo(name = "username")
	private String username;

	@SerializedName("email")
	@ColumnInfo(name = "email")
	private String email;

	@SerializedName("wallet_id")
	@ColumnInfo(name = "wallet_id")
	private int walletId;

	@SerializedName("saldo")
	@ColumnInfo(name = "saldo")
	private int saldo;

	protected DataItem(Parcel in) {
		name = in.readString();
		createdAt = in.readString();
		updatedAt = in.readString();
		phoneNumber = in.readString();
		id = in.readInt();
		avatar = in.readString();
		status = in.readString();
		userId = in.readInt();
		username = in.readString();
		email = in.readString();
		walletId = in.readInt();
		saldo = in.readInt();
	}

	public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
		@Override
		public DataItem createFromParcel(Parcel in) {
			return new DataItem(in);
		}

		@Override
		public DataItem[] newArray(int size) {
			return new DataItem[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",phone_number = '" + phoneNumber + '\'' + 
			",id = '" + id + '\'' + 
			",avatar = '" + avatar + '\'' +
			",status = '" + status + '\'' +
			",user_id = '" + userId + '\'' +
			",username = '" + username + '\'' +
			",wallet_id = '" + walletId + '\'' +
			",saldo = '" + saldo + '\'' +
			",email = '" + email + '\'' +
			",updated_at = '" + updatedAt + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(createdAt);
		dest.writeString(updatedAt);
		dest.writeString(phoneNumber);
		dest.writeInt(id);
		dest.writeString(avatar);
		dest.writeString(status);
		dest.writeInt(userId);
		dest.writeString(username);
		dest.writeString(email);
		dest.writeInt(walletId);
		dest.writeInt(saldo);
	}
}