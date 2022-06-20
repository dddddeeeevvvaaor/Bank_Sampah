package com.example.Bank_Sampah.model.user;

public class DataModel {
    private int Id_User;
    private String Nama, Alamat, Email, Password;

    public int getId() {
        return Id_User;
    }

    public void setId(int Id_User) {
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