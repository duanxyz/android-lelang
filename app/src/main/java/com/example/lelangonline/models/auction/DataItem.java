package com.example.lelangonline.models.auction;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lelangonline.utils.Constants;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Constants.ITEMS_TABLE_NAME1)
public class DataItem implements Parcelable {

	public DataItem(){
	}
	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("offer")
	@ColumnInfo(name = "offer")
	private int offer;

	@SerializedName("updated_at")
	@ColumnInfo(name = "updated_at")
	private String updatedAt;

	@SerializedName("item_id")
	@ColumnInfo(name = "item_id")
	private String itemId;

	@SerializedName("bidder_username")
	@ColumnInfo(name = "bidder_username")
	private String bidderUsername;

	@SerializedName("created_at")
	@ColumnInfo(name = "created_at")
	private String createdAt;

	protected DataItem(Parcel in) {
		offer = in.readInt();
		updatedAt = in.readString();
		itemId = in.readString();
		bidderUsername = in.readString();
		createdAt = in.readString();
		id = in.readInt();
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

	public void setOffer(int offer){
		this.offer = offer;
	}

	public int getOffer(){
		return offer;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemId(){
		return itemId;
	}

	public void setBidderUsername(String bidderUsername){
		this.bidderUsername = bidderUsername;
	}

	public String getBidderUsername(){
		return bidderUsername;
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
			"offer = '" + offer + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",bidder_username = '" + bidderUsername + '\'' + 
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
		dest.writeInt(offer);
		dest.writeString(updatedAt);
		dest.writeString(itemId);
		dest.writeString(bidderUsername);
		dest.writeString(createdAt);
		dest.writeInt(id);
	}
}