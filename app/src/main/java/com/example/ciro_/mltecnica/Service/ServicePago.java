package com.example.ciro_.mltecnica.Service;

import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.Cuotas;
import com.example.ciro_.mltecnica.Model.MetodoDePago;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicePago {

    @GET("payment_methods")
    Call<List<MetodoDePago>> getMediosDePago(@Query("public_key") String apiKey);

    @GET("payment_methods/card_issuers")
    Call<List<Banco>> getBancos(@Query("public_key") String apiKey,
                                   @Query("payment_method_id") String payment_method_id);

    @GET("payment_methods/installments")
    Call<List<Cuotas>>getCuotas (@Query("public_key") String apiKey,
                                       @Query("amount") Float amount,
                                       @Query("payment_method_id") String payment_method_id,
                                       @Query("issuer.id") String issuer);

}
