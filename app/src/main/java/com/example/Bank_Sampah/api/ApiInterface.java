package com.example.Bank_Sampah.api;

import com.example.Bank_Sampah.model.Login.Login;
import com.example.Bank_Sampah.model.Register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("Email") String Email,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("Nama") String Nama,
            @Field("Alamat") String Alamat,
            @Field("Email") String Email,
            @Field("Password") String Password
    );
}