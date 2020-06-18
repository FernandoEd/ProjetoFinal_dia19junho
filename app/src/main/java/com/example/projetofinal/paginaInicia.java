package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class paginaInicia extends AppCompatActivity {
    boolean bool = false;
    boolean emailOut = getIntent().getBooleanExtra("name", bool);
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.adminLayout);
        if(emailOut)
        {
            register.setVisible(true);
        }
        else
        {
            register.setVisible(false);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicia);
        TextView textView = (TextView) findViewById(R.id.textView);



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

    public void VerDados(View view) {
        Intent intent = new Intent(this,VerDadosGuardados.class);
        startActivity(intent);
    }
}
