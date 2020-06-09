package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebse connection Success", Toast.LENGTH_LONG).show();

    }

    public void Confirmar(View view) {
        Intent intent = new Intent(this,paginaInicia.class);
        startActivity(intent);
    }
    public void enviarParaRegisto(View view) {
        Intent intent = new Intent(this,pagina_registo.class);
        startActivity(intent);
    }

   }
