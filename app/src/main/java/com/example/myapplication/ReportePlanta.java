package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;

public class ReportePlanta extends AppCompatActivity {
    private Button buttonContinuar, buttonAtras;
    private EditText nombre, iluminacion, temperatura, humedad;
    private TextView textAqui;
    private FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_planta);

        nombre = (EditText)findViewById(R.id.textNombre);
        iluminacion = (EditText)findViewById(R.id.textIlumC);
        temperatura = (EditText)findViewById(R.id.textTmp);
        humedad = (EditText)findViewById(R.id.textRiego);

        database = FirebaseDatabase.getInstance();

        buttonAtras = (Button)findViewById(R.id.buttonAtras);
        buttonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atras(buttonAtras);
            }
        });

        buttonContinuar = (Button)findViewById(R.id.buttonNext);
        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(buttonContinuar);
            }
        });

        textAqui = (TextView) findViewById(R.id.textAQUI);
        textAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiar(textAqui);
            }
        });
    }

    private void cambiar(TextView textAqui) {
        Intent i = new Intent(this,Monitoreo.class);
        startActivity(i);
    }

    private void next(Button buttonContinuar) {
        DatabaseReference reference = database.getReference("Usuario1");

        String name = nombre.getText().toString().trim();
        String ilum = iluminacion.getText().toString().trim();
        String [] partesIlum = ilum.split("-");
        String ilumInf = partesIlum[0].trim();
        String ilumSup = partesIlum[1].trim();
        String hum = humedad.getText().toString().trim();
        String [] partesHum = ilum.split("-");
        String humInf = partesHum[0].trim();
        String humSup = partesHum[1].trim();
        String temp = temperatura. getText().toString().trim();
        String [] partesTemp = ilum.split("-");
        String tempInf = partesTemp[0].trim();
        String tempSup = partesTemp[1].trim();

        reference.child("nombre").setValue(name);
        reference.child("ilumInf").setValue(ilumInf);
        reference.child("ilumSup").setValue(ilumSup);
        reference.child("tempInf").setValue(tempInf);
        reference.child("tempSup").setValue(tempSup);
        reference.child("humInf").setValue(humInf);
        reference.child("humSup").setValue(humSup);

        Toast.makeText(getApplicationContext(),"Su planta ha sido registrada",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Monitoreo.class);
        startActivity(i);
    }

    private void atras(Button buttonAtras) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}