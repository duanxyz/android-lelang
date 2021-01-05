package com.example.lelangonline.models.deposits;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("deposit")
	private Deposit deposit;

	@SerializedName("saldo")
	private int saldo;

	@SerializedName("user")
	private User user;

	public void setDeposit(Deposit deposit){
		this.deposit = deposit;
	}

	public Deposit getDeposit(){
		return deposit;
	}

	public void setSaldo(int saldo){
		this.saldo = saldo;
	}

	public int getSaldo(){
		return saldo;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"deposit = '" + deposit + '\'' + 
			",saldo = '" + saldo + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}