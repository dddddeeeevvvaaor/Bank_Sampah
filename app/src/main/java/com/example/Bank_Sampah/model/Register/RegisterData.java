package com.example.Bank_Sampah.model.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("Email")
	private String email;

	@SerializedName("Nama")
	private String nama;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}
}