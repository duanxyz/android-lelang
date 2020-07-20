package com.example.lelangonline.models.balance;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

<<<<<<< HEAD
import com.example.lelangonline.models.auction.DataItem;
=======
import com.example.lelangonline.models.auction.Auction;
>>>>>>> b1ad87c... error
import com.google.gson.annotations.SerializedName;

public class Balance implements Parcelable {

	@SerializedName("wallet_id")
	@ColumnInfo( name = "wallet_id")
	private String walletId;

	@SerializedName("keterangan")
	@ColumnInfo( name = "keterangan")
	private String keterangan;

	@SerializedName("nominal")
	@ColumnInfo( name = "nominal")
	private int nominal;

	@SerializedName("updated_at")
	@ColumnInfo( name = "updated_at")
	private String updatedAt;

	@SerializedName("created_at")
<<<<<<< HEAD
	@ColumnInfo( name = "created_at")
	private String createdAt;

	@SerializedName("ending_balance")
	@ColumnInfo( name = "ending_balance")
=======
	@ColumnInfo(name = "created_at")
	private String createdAt;

	@SerializedName("ending_balance")
	@ColumnInfo(name = "ending_balance")
>>>>>>> b1ad87c... error
	private int endingBalance;

	@PrimaryKey
	@SerializedName("id")
	private int id;

	protected Balance(Parcel in) {
		walletId = in.readString();
		keterangan = in.readString();
		nominal = in.readInt();
		updatedAt = in.readString();
		createdAt = in.readString();
		endingBalance = in.readInt();
		id = in.readInt();
	}

	public static final DiffUtil.ItemCallback<Balance> CALLBACK = new DiffUtil.ItemCallback<Balance>() {
		@Override
		public boolean areItemsTheSame(@NonNull Balance balance, @NonNull Balance balance2) {
			return balance.getId() == balance2.getId();
		}

		@Override
		public boolean areContentsTheSame(@NonNull Balance balance, @NonNull Balance balance2) {
			return true;
		}
	};

	public static final Creator<Balance> CREATOR = new Creator<Balance>() {
		@Override
		public Balance createFromParcel(Parcel in) {
			return new Balance(in);
		}

		@Override
		public Balance[] newArray(int size) {
			return new Balance[size];
		}
	};

	public void setWalletId(String walletId){
		this.walletId = walletId;
	}

	public String getWalletId(){
		return walletId;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setNominal(int nominal){
		this.nominal = nominal;
	}

	public int getNominal(){
		return nominal;
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

	public void setEndingBalance(int endingBalance){
		this.endingBalance = endingBalance;
	}

	public int getEndingBalance(){
		return endingBalance;
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
			"wallet_id = '" + walletId + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",nominal = '" + nominal + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",ending_balance = '" + endingBalance + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(walletId);
		dest.writeString(keterangan);
		dest.writeInt(nominal);
		dest.writeString(updatedAt);
		dest.writeString(createdAt);
		dest.writeInt(endingBalance);
		dest.writeInt(id);
	}
}