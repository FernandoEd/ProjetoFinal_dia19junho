package com.example.projetofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class paginaInicia extends AppCompatActivity {

    boolean emailOut;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean bool = false;
        emailOut = getIntent().getBooleanExtra("name", bool);
        if(emailOut){
             this.setContentView(R.layout.activity_pagina_inicia);
            setTheme(R.menu.menu);// (for Custom theme)
           this.setTitle("Admin Mode");
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }else {
            setTheme(R.style.AppTheme); // (for Custom theme)
            this.setContentView(R.layout.activity_pagina_inicia);
        }
  }



    public void Compras(View view) {
        Intent intent = new Intent(this,Tabela_Compras.class);
        intent.putExtra("name",emailOut);
        startActivity(intent);

    }

    public void Combustivel(View view) {
        Intent intent = new Intent(this,Combustivel.class);
        startActivity(intent);
    }

    public void Restaurantes(View view) {
        Intent intent = new Intent(this,Restaurantes.class);
        startActivity(intent);
    }

    public void Cafe_bares(View view) {

        Intent intent = new Intent(this,VerDadosGuardados.class);
        intent.putExtra("name",emailOut);
        startActivity(intent);
    }

    public void VerDados(View view) {
        Intent intent = new Intent(this,VerDadosGuardados.class);
        startActivity(intent);
    }
}
