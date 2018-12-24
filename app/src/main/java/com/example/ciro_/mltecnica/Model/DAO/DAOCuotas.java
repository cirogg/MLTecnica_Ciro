package com.example.ciro_.mltecnica.Model.DAO;

import android.util.Log;

import com.example.ciro_.mltecnica.Model.Cuotas;
import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.Service.ServicePago;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOCuotas {

    String BASE_URL = "https://api.mercadopago.com/v1/";
    String apiKey = "444a9ef5-8a6b-429f-abdf-587639155d88";

    ServicePago servicePago;

    public void getCuotas(String monto, String metodoDePagoID, String bancoSeleccionadoID,ResultListener<List<Cuotas>> escuchadorDelControlador){
        crearRetrofit();

        callCuotas(escuchadorDelControlador,monto,metodoDePagoID,bancoSeleccionadoID);
    }

    private void crearRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePago = retrofit.create(ServicePago.class);
    }

    private void callCuotas(final ResultListener<List<Cuotas>> escuchadorDelControlador,String monto, String metodoDePagoID, String bancoSeleccionadoID){
        Call<List<Cuotas>> listaCallCuotas = servicePago.getCuotas(apiKey,Float.parseFloat(monto),metodoDePagoID,bancoSeleccionadoID);
        listaCallCuotas.enqueue(new Callback<List<Cuotas>>() {
            @Override
            public void onResponse(Call<List<Cuotas>> call, Response<List<Cuotas>> response) {
                Log.d("debug","LLEGAMOS AL DAO METODOS DE PAGO");
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<List<Cuotas>> call, Throwable t) {
                Log.d("debug","ERROR DAO METODOS DE PAGO");
            }
        });
    }

}
