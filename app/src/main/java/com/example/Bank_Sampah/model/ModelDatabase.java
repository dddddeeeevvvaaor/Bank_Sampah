package com.example.Bank_Sampah.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbl_banksampah")
public class ModelDatabase implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public int uid;

    @ColumnInfo(name = "kategori")
    public String kategori;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "nama_pengguna")
    public String nama_pengguna;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "catatan")
    public String catatan;

    @ColumnInfo(name = "berat")
    public String berat;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama_Pengguna() {
        return nama_pengguna;
    }

    public void setNama_Pengguna(String nama_pengguna) {
        this.nama_pengguna = nama_pengguna;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getcatatan() {
        return catatan;
    }

    public void setcatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getberat() {
        return berat;
    }

    public void setberat(String berat) {this.berat = berat;}
}
