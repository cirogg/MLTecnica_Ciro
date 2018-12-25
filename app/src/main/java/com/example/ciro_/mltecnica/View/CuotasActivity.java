package com.example.ciro_.mltecnica.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ciro_.mltecnica.Controller.CuotasController;
import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.Cuotas;
import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.Model.PayerCost;
import com.example.ciro_.mltecnica.R;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class CuotasActivity extends AppCompatActivity {

    private Float monto;
    private String metodoDePagoID;
    private String bancoSeleccionadoID;
    private String bancoSeleccionadoNombre;
    private List<Cuotas> listaDeCuotas;

    private Cuotas cuotaSeleccionada;
    private List<PayerCost> listaDePayerCost;
    private PayerCost payerCostSeleccionado;
    private String planSeleccionado;

    private TextView textViewMonto;
    private Spinner spinnerCuotas;
    private ArrayAdapter arrayAdapter;
    private Button botonNext;

    private CuotasController cuotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuotas);

        spinnerCuotas = findViewById(R.id.spinnerCuota);
        botonNext = findViewById(R.id.cmdNextCuota);
        textViewMonto = findViewById(R.id.textViewMontoCuota);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monto = bundle.getFloat("monto");
        metodoDePagoID = bundle.getString("id");
        bancoSeleccionadoID = bundle.getString("idBanco");
        bancoSeleccionadoNombre = bundle.getString("nombreBanco");
        textViewMonto.setText(monto.toString());

        cuotasController = new CuotasController();
        cuotasController.getCuotas(monto.toString(),metodoDePagoID,bancoSeleccionadoID, new ResultListener<List<Cuotas>>() {
            @Override
            public void finish(List<Cuotas> resultado) {
                listaDeCuotas = resultado;
                obtenerPayerCost(listaDeCuotas);
                arrayAdapter = new ArrayAdapter(CuotasActivity.this,android.R.layout.simple_dropdown_item_1line, listaDePayerCost);
                spinnerCuotas.setAdapter(arrayAdapter);
            }
        }); //Monto, idMetodoDePago, idBanco

        spinnerCuotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payerCostSeleccionado = listaDePayerCost.get(position);
                planSeleccionado = payerCostSeleccionado.getRecommendedMessage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        botonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });


    }

    private void nextActivity(){
        Intent intent = new Intent(this,FinalActivity.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("monto",monto);
        bundle.putString("id", metodoDePagoID);
        bundle.putString("idBanco",bancoSeleccionadoID);
        bundle.putString("nombreBanco", bancoSeleccionadoNombre);
        bundle.putString("cuotas",planSeleccionado);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void obtenerPayerCost(List<Cuotas> listaDeCuotas){
        for (Cuotas listaDeCuota : listaDeCuotas) {
            listaDePayerCost = listaDeCuota.getPayerCosts();
        }
    }
}
