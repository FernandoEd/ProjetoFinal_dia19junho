package com.example.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class paginaInicia extends AppCompatActivity {

    boolean emailOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean bool = false;
        emailOut = getIntent().getBooleanExtra("name", bool);
        if(emailOut){
            setTheme(R.style.AppTheme_Others); // (for Custom theme)
            this.setContentView(R.layout.activity_pagina_inicia);
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
        Intent intent = new Intent(this,Cafe_bares.class);
        startActivity(intent);
    }

    public void VerDados(View view) {
        Intent intent = new Intent(this,VerDadosGuardados.class);
        startActivity(intent);
    }
}
