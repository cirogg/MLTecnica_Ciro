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

import com.example.ciro_.mltecnica.Controller.MetodoDePagoController;
import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.R;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class MetodoDePagoActivity extends AppCompatActivity {

    private Float monto;
    private String metodoDePagoID;
    private List<MetodoDePago> listaMetodosDePago;

    private MetodoDePago metodoDePagoSeleccionado;

    private TextView textViewMonto;
    private Spinner spinnerMetodo;
    ArrayAdapter arrayAdapter;
    private Button botonNext;

    private MetodoDePagoController metodoDePagoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_de_pago);

        spinnerMetodo = findViewById(R.id.spinnerMetodo);
        botonNext = findViewById(R.id.cmdNextMetodo);
        textViewMonto = findViewById(R.id.textViewMontoMetodo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monto = bundle.getFloat("monto");
        textViewMonto.setText(monto.toString());

        metodoDePagoController = new MetodoDePagoController();
        metodoDePagoController.getMetodosDePago(new ResultListener<List<MetodoDePago>>() {
            @Override
            public void finish(List<MetodoDePago> resultado) {
                listaMetodosDePago = resultado;
                arrayAdapter = new ArrayAdapter(MetodoDePagoActivity.this,android.R.layout.simple_dropdown_item_1line, listaMetodosDePago);
                spinnerMetodo.setAdapter(arrayAdapter);
            }
        });

        spinnerMetodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                metodoDePagoSeleccionado = listaMetodosDePago.get(position);
                metodoDePagoID = metodoDePagoSeleccionado.getId();
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
        Intent intent = new Intent(this,BancoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("monto",monto);
        bundle.putString("id",metodoDePagoID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
