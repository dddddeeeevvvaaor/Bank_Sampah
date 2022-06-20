package com.example.Bank_Sampah.model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("Id_User")
	private int idUser;

	@SerializedName("Email")
	private String email;

	@SerializedName("Nama")
	private String nama;

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

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