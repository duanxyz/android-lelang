package com.example.lelangonline.models.deposits;

import com.google.gson.annotations.SerializedName;

public class ResponseDeposit{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDeposit{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}