package com.example.inmobiliaria.retrofit.services;

import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.responses.DetallesResponseContainer;
import com.example.inmobiliaria.responses.ResponseContainer;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;


public interface InmuebleService {

    @GET("/properties")
    Call<ResponseContainer<Inmueble>> listInmueble();

    @GET("/properties/{id}")
    Call<DetallesResponseContainer<Inmueble>> getInmueble(@Path("id") String id);

}
