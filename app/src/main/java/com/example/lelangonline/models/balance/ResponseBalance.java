package com.example.lelangonline.models.balance;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBalance{

	@SerializedName("data")
	private List<Balance> data;

	public void setData(List<Balance> data){
		this.data = data;
	}

	public List<Balance> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBalance{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}