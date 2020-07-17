package com.example.lelangonline.models.users;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Saldo implements Parcelable {

	@SerializedName("member_id")
	@ColumnInfo(name = "member_id")
	private String memberId;

	@SerializedName("balance")
	@ColumnInfo(name = "balance")
	private int balance;

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@SerializedName("id")
	@ColumnInfo(name = "id")
	private int id;

	protected Saldo(Parcel in) {
		memberId = in.readString();
		balance = in.readInt();
		updatedAt = in.readString();
		createdAt = in.readString();
		id = in.readInt();
	}

	public static final Creator<Saldo> CREATOR = new Creator<Saldo>() {
		@Override
		public Saldo createFromParcel(Parcel in) {
			return new Saldo(in);
		}

		@Override
		public Saldo[] newArray(int size) {
			return new Saldo[size];
		}
	};

	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Saldo{" + 
			"member_id = '" + memberId + '\'' + 
			",balance = '" + balance + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(memberId);
		dest.writeInt(balance);
		dest.writeString(updatedAt);
		dest.writeString(createdAt);
		dest.writeInt(id);
	}
}