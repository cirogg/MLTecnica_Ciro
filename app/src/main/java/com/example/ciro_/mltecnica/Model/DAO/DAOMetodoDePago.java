package com.example.ciro_.mltecnica.Model.DAO;

import android.util.Log;
import android.widget.Toast;

import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.Service.ServicePago;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOMetodoDePago {

    String BASE_URL = "https://api.mercadopago.com/v1/";
    String apiKey = "444a9ef5-8a6b-429f-abdf-587639155d88";

    ServicePago servicePago;

    public void getMetodosDePago(ResultListener<List<MetodoDePago>> escuchadorDelControlador){

        crearRetrofit();

        callMediosDePago(escuchadorDelControlador);



    }

    private void crearRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePago = retrofit.create(ServicePago.class);
    }

    private void callMediosDePago(final ResultListener<List<MetodoDePago>> escuchadorDelControlador){
        Call<List<MetodoDePago>> listaCallMetodoDePago = servicePago.getMediosDePago(apiKey);
        listaCallMetodoDePago.enqueue(new Callback<List<MetodoDePago>>() {
            @Override
            public void onResponse(Call<List<MetodoDePago>> call, Response<List<MetodoDePago>> response) {
                Log.d("debug","LLEGAMOS AL DAO METODOS DE PAGO");
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<List<MetodoDePago>> call, Throwable t) {
                Log.d("debug","ERROR DAO METODOS DE PAGO");
            }
        });
    }

}
