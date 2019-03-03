package com.example.inmobiliaria.retrofit.services;

import com.example.inmobiliaria.responses.CategoriaResponse;
import com.example.inmobiliaria.responses.ResponseContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaService {

    @GET("/categories")
    Call<ResponseContainer<CategoriaResponse>> listCategorias();

}
