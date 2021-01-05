package com.example.lelangonline.models.banks;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lelangonline.utils.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Constants.BANKS_TABLE_NAME)
public class Banks implements Parcelable {

	public Banks(){}

	@PrimaryKey@NonNull
	@SerializedName("account_number")
	private String accountNumber;

	@SerializedName("updated_at")
	@ColumnInfo( name = "updated_at")
	private String updatedAt;

	@SerializedName("bank_name")
	@ColumnInfo( name = "bank_name")
	private String bankName;

	@SerializedName("name")
	@ColumnInfo( name = "name")
	private String name;

	@SerializedName("photo")
	@ColumnInfo( name = "photo")
	private String photo;

	@SerializedName("created_at")
	@ColumnInfo( name = "created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	protected Banks(Parcel in) {
		accountNumber = in.readString();
		updatedAt = in.readString();
		bankName = in.readString();
		name = in.readString();
		photo = in.readString();
		createdAt = in.readString();
		id = in.readInt();
	}

	public static final DiffUtil.ItemCallback<Banks> CALLBACK = new DiffUtil.ItemCallback<Banks>() {
		@Override
		public boolean areItemsTheSame(@NonNull Banks  dataItem, @NonNull Banks dataItem2) {
			return dataItem.getId() == dataItem2.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull Banks dataItem, @NonNull Banks dataItem2) {
			return true;
		}
	};

	public static final Creator<Banks> CREATOR = new Creator<Banks>() {
		@Override
		public Banks createFromParcel(Parcel in) {
			return new Banks(in);
		}

		@Override
		public Banks[] newArray(int size) {
			return new Banks[size];
		}
	};

	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber(){
		return accountNumber;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
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
			"DataItem{" + 
			"account_number = '" + accountNumber + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",bank_name = '" + bankName + '\'' + 
			",name = '" + name + '\'' + 
			",photo = '" + photo + '\'' + 
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
		dest.writeString(accountNumber);
		dest.writeString(updatedAt);
		dest.writeString(bankName);
		dest.writeString(name);
		dest.writeString(photo);
		dest.writeString(createdAt);
		dest.writeInt(id);
	}
}