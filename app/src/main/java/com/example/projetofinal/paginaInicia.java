package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class paginaInicia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicia);
    }

    public void Compras(View view) {
        Intent intent = new Intent(this,Tabela_Compras.class);
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
}
