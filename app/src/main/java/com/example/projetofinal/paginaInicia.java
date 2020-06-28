package com.example.projetofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class paginaInicia extends AppCompatActivity {
    boolean change;
    boolean emailOut;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean bool = false;
        emailOut = getIntent().getBooleanExtra("name", bool);
        if(emailOut){
            change=true;
            this.setContentView(R.layout.activity_pagina_inicia);

             setTheme(R.menu.menu);// (for Custom theme)
             this.setTitle("Admin Mode");
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }else {

            setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar); // (for Custom theme)
            this.setContentView(R.layout.activity_pagina_inicia);
            emailOut=false;
        }
  }


    public void Compras(View view) {
        Intent intent = new Intent(this,VerDadosGuardados_Compras.class);
        intent.putExtra("change",change);
        startActivity(intent);

    }

    public void Combustivel(View view) {
        Intent intent = new Intent(this,VerDadosGuardados_Combustivel.class);
        intent.putExtra("change",change);
        startActivity(intent);
    }

    public void Restaurantes(View view) {
        Intent intent = new Intent(this,VerDadosGuardados_Restaurantes.class);
        intent.putExtra("change",change);
        startActivity(intent);
    }

    public void Cafe_bares(View view) {

        Intent intent = new Intent(this, VerDadosGuardados_Cafe_Bares.class);
        intent.putExtra("change",change);
        startActivity(intent);
    }

    public void VerDados(View view) {
        Intent intent = new Intent(this, VerDadosGuardados_Cafe_Bares.class);
        intent.putExtra("change",change);
        startActivity(intent);
    }
}
