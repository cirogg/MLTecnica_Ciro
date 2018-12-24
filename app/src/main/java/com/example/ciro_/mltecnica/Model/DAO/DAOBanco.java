package com.example.ciro_.mltecnica.Model.DAO;

import android.util.Log;

import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Service.ServicePago;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOBanco {

    String BASE_URL = "https://api.mercadopago.com/v1/";
    String apiKey = "444a9ef5-8a6b-429f-abdf-587639155d88";

    ServicePago servicePago;

    public void getBancos(ResultListener<List<Banco>> escuchadorDelControlador, String metodoDePagoID){

        crearRetrofit();

        callBancos(escuchadorDelControlador,metodoDePagoID);

    }

    private void crearRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePago = retrofit.create(ServicePago.class);
    }

    private void callBancos(final ResultListener<List<Banco>>escuchadorDelControlador, String metodoDePagoID){
        Call<List<Banco>> listaCallDeBancos = servicePago.getBancos(apiKey,metodoDePagoID);
        listaCallDeBancos.enqueue(new Callback<List<Banco>>() {
            @Override
            public void onResponse(Call<List<Banco>> call, Response<List<Banco>> response) {
                Log.d("debug","LLEGAMOS AL DAO BANCO");
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<List<Banco>> call, Throwable t) {
                Log.d("debug","ERROR EN DAO BANCO");
            }
        });
    }

}
