package com.example.ciro_.mltecnica.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.R;

public class MainActivity extends AppCompatActivity {

    Float monto;

    Button botonNextMonto;
    EditText editTextMonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonNextMonto = findViewById(R.id.cmdNextMonto);
        editTextMonto = findViewById(R.id.editTextMonto);

        botonNextMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editTextMonto.getText().toString().isEmpty())){
                    monto = Float.parseFloat(editTextMonto.getText().toString());
                    nextActivity();
                }
            }
        });

    }

    private void nextActivity(){
        Intent intent = new Intent(this,MetodoDePagoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putFloat("monto",monto);
        intent.putExtras(bundle);
        startActivity(intent);
    }



}
