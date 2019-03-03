package com.example.inmobiliaria.retrofit.services;

import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.responses.DetallesResponseContainer;
import com.example.inmobiliaria.responses.InmuebleResponse;
import com.example.inmobiliaria.responses.ResponseContainer;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface InmuebleService {

    @GET("/properties")
    Call<ResponseContainer<Inmueble>> listInmueble();

    @GET("/properties/{id}")
    Call<DetallesResponseContainer<Inmueble>> getInmueble(@Path("id") String id);

    @GET("/properties/fav")
    Call<ResponseContainer<Inmueble>> getFavoritos();

    @POST("/properties/fav/{id}")
    Call<InmuebleResponse> addFavoritos(@Path("id") String id);

    @DELETE("/properties/fav/{id}")
    Call<InmuebleResponse> deleteFavoritos(@Path("id") String id);

    @POST("/properties")
    Call<Inmueble> addInmueble();

}
