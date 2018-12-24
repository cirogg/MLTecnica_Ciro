package com.example.ciro_.mltecnica.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ciro_.mltecnica.Controller.BancoController;
import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.Cuotas;
import com.example.ciro_.mltecnica.R;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class BancoActivity extends AppCompatActivity {

    private Float monto;
    private String metodoDePagoID;
    private List<Banco> listaDeBancos;

    private Banco bancoSeleccionado;
    private String bancoSeleccionadoID;

    private TextView textViewMonto;
    private Spinner spinnerBancos;
    private ArrayAdapter arrayAdapter;
    private Button botonNext;

    private BancoController bancoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco);

        spinnerBancos = findViewById(R.id.spinnerBanco);
        botonNext = findViewById(R.id.cmdNextBanco);
        textViewMonto = findViewById(R.id.textViewMontoBanco);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monto = bundle.getFloat("monto");
        metodoDePagoID = bundle.getString("id");
        textViewMonto.setText(monto.toString());

        bancoController = new BancoController();
        bancoController.getBancos(new ResultListener<List<Banco>>() {
            @Override
            public void finish(List<Banco> resultado) {
                listaDeBancos = resultado;
                arrayAdapter = new ArrayAdapter(BancoActivity.this,android.R.layout.simple_dropdown_item_1line,listaDeBancos);
                spinnerBancos.setAdapter(arrayAdapter);
            }
        },metodoDePagoID);


        spinnerBancos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bancoSeleccionado = listaDeBancos.get(position);
                bancoSeleccionadoID = bancoSeleccionado.getId();
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
        Intent intent = new Intent(this,CuotasActivity.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("monto",monto);
        bundle.putString("id", metodoDePagoID);
        bundle.putString("idBanco",bancoSeleccionadoID);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
