package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Monitoreo extends AppCompatActivity {
    private Button buttonSalir;
    private TextView temperatura, iluminacion, humedad;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Usuario1");

        temperatura = (TextView)findViewById(R.id.textTempC);
        iluminacion = (TextView)findViewById(R.id.textIlumC);
        humedad = (TextView)findViewById(R.id.textHumC);

        buttonSalir = (Button)findViewById(R.id.buttonSalir);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir(buttonSalir);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String T = snapshot.child("temp").getValue().toString();
                String I = snapshot.child("ilum").getValue().toString();
                String H = snapshot.child("hum").getValue().toString();

                temperatura.setText(T);
                iluminacion.setText(I);
                humedad.setText(H);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void salir(Button buttonSalir) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}