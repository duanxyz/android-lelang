package com.example.lelangonline.data.model;

import com.google.gson.annotations.SerializedName;

public class ResponseMember{

	@SerializedName("data")
	private DataMember dataMember;

	public void setDataMember(DataMember dataMember){
		this.dataMember = dataMember;
	}

	public DataMember getDataMember(){
		return dataMember;
	}

	@Override
 	public String toString(){
		return 
			"ResponseMember{" + 
			"data = '" + dataMember + '\'' +
			"}";
		}
}