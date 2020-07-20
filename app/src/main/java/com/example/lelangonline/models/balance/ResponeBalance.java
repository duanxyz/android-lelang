package com.example.lelangonline.models.balance;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponeBalance{

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
			"ResponeBalance{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}