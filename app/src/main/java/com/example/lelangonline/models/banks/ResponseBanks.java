package com.example.lelangonline.models.banks;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBanks{

	@SerializedName("data")
	private List<Banks> data;

	public void setData(List<Banks> data){
		this.data = data;
	}

	public List<Banks> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBanks{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}