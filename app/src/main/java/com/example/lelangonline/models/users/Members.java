package com.example.lelangonline.models.users;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Members{

	@SerializedName("data")
	private List<DataItem> data = null;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Members{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}