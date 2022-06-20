package com.example.Bank_Sampah.model.user;

import java.io.Serializable;

public class user implements Serializable {
    String Id_User;
    String Nama;
    String Alamat;
    String Email;
    String Password;

    public user() {
    }

    public user(String Id_User, String Nama, String Alamat, String Email, String Password) {
        this.Id_User = Id_User;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.Email = Email;
        this.Password = Password;
    }

    public String getId_User() {
        return Id_User;
    }

    public void setId(String Id_User) {
        this.Id_User = Id_User;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}