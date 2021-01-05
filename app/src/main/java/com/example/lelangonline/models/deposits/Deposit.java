package com.example.lelangonline.models.deposits;

import com.google.gson.annotations.SerializedName;

public class Deposit{

	@SerializedName("wallet_id")
	private String walletId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("sent_date")
	private String sentDate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("proof")
	private String proof;

	@SerializedName("id")
	private int id;

	public void setWalletId(String walletId){
		this.walletId = walletId;
	}

	public String getWalletId(){
		return walletId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setSentDate(String sentDate){
		this.sentDate = sentDate;
	}

	public String getSentDate(){
		return sentDate;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setProof(String proof){
		this.proof = proof;
	}

	public String getProof(){
		return proof;
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
			"Deposit{" + 
			"wallet_id = '" + walletId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",sent_date = '" + sentDate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",proof = '" + proof + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}