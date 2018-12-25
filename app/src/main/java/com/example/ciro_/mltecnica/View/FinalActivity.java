package com.example.ciro_.mltecnica.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ciro_.mltecnica.R;

public class FinalActivity extends AppCompatActivity {

    TextView textViewMonto;
    TextView textViewMetodo;
    TextView textViewBanco;
    TextView textViewCuotas;

    private Float monto;
    private String metodo;
    private String banco;
    private String cuotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        textViewMonto = findViewById(R.id.textViewFinalMonto);
        textViewMetodo = findViewById(R.id.textViewFinalMetodo);
        textViewBanco = findViewById(R.id.textViewFinalMBanco);
        textViewCuotas = findViewById(R.id.textViewFinalMCuotas);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        monto = bundle.getFloat("monto");
        metodo = bundle.getString("id");
        banco = bundle.getString("nombreBanco");
        cuotas = bundle.getString("cuotas");

        textViewMonto.setText(monto.toString());
        textViewMetodo.setText(metodo);
        textViewBanco.setText(banco);
        textViewCuotas.setText(cuotas);


    }
}
