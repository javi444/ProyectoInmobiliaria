package com.example.inmobiliaria.retrofit.services;


import com.example.inmobiliaria.models.Registro;
import com.example.inmobiliaria.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/auth")
    Call<LoginResponse> doLogin();

    @POST("/users")
    Call<LoginResponse> doRegister(@Body Registro registro);
}
