package com.example.lelangonline.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lelangonline.models.converters.SourceConverter;
import com.example.lelangonline.utils.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Constants.ITEMS_TABLE_NAME)
//@TypeConverters(SourceConverter.class)
public class DataItem implements Parcelable {

	public DataItem(){
	}
	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("photo")
	@ColumnInfo(name = "photo")
	private String photo;

	@SerializedName("initial_price")
	@ColumnInfo(name = "initial_price")
	private int initialPrice;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@SerializedName("item_name")
	@ColumnInfo(name = "item_name")
	private String itemName;

	@SerializedName("deskripsi")
	@ColumnInfo(name = "deskripsi")
	private String deskripsi;

	@SerializedName("category")
	@ColumnInfo(name = "category")
	private String category;

	@SerializedName("status")
	@ColumnInfo(name = "status")
	private String status;

	@SerializedName("auction_date")
	@ColumnInfo(name = "auction_date")
	private String auctionDate;

	@SerializedName("auction_time")
	@ColumnInfo(name = "auction_time")
	private String auctionTime;

	protected DataItem(Parcel in) {
		updatedAt = in.readString();
		photo = in.readString();
		initialPrice = in.readInt();
		createdAt = in.readString();
		itemName = in.readString();
		id = in.readInt();
		deskripsi = in.readString();
		category = in.readString();
		status = in.readString();
		auctionDate = in.readString();
		auctionTime = in.readString();
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setInitialPrice(int initialPrice){
		this.initialPrice = initialPrice;
	}

	public int getInitialPrice(){
		return initialPrice;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setAuctionDate(String auctionDate){
		this.auctionDate = auctionDate;
	}

	public String getAuctionDate(){
		return auctionDate;
	}

	public void setAuctionTime(String auctionTime){
		this.auctionTime = auctionTime;
	}

	public String getAuctionTime(){
		return auctionTime;
	}

	@Override
 	public String toString(){
		return
			"DataItem{" +
			"updated_at = '" + updatedAt + '\'' +
			",photo = '" + photo + '\'' +
			",initial_price = '" + initialPrice + '\'' +
			",created_at = '" + createdAt + '\'' +
			",item_name = '" + itemName + '\'' +
			",id = '" + id + '\'' +
			",deskripsi = '" + deskripsi + '\'' +
			",category = '" + category + '\'' +
			",status = '" + status + '\'' +
			",auction_date = '" + auctionDate + '\'' +
			",auction_time = '" + auctionTime + '\'' +
			"}";
		}

	public static final DiffUtil.ItemCallback<DataItem> CALLBACK = new DiffUtil.ItemCallback<DataItem>() {
		@Override
		public boolean areItemsTheSame(@NonNull DataItem  dataItem, @NonNull DataItem dataItem2) {
			return dataItem.getId() == dataItem2.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull DataItem dataItem, @NonNull DataItem dataItem2) {
			return true;
		}
	};


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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(updatedAt);
		dest.writeString(photo);
		dest.writeInt(initialPrice);
		dest.writeString(createdAt);
		dest.writeString(itemName);
		dest.writeInt(id);
		dest.writeString(deskripsi);
		dest.writeString(category);
		dest.writeString(status);
		dest.writeString(auctionDate);
		dest.writeString(auctionTime);
	}
}