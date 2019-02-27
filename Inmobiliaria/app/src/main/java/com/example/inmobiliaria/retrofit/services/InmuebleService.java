package com.example.inmobiliaria.retrofit.services;

import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.responses.ResponseContainer;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;


public interface InmuebleService {

    @GET("/properties")
    Call<ResponseContainer<Inmueble>> listInmueble();

}
