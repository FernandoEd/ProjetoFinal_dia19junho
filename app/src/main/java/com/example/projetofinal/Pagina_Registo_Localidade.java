package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pagina_Registo_Localidade extends AppCompatActivity {
    DatabaseReference reff;
    Members member;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_registo);
    }
   /* public void Registo(View view) {
        Intent intent = new Intent(this, paginaInicia.class);
        try {


            EditText nome = (EditText) findViewById(R.id.nomeRegisto);

            String nomeReg = nome.getText().toString();
            String local = localidade.getText().toString();

            member = new Members();
            if (nomeReg.length() <= 3) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            } else if ((idadefinal < 0) || (idadefinal > 120) ) {
                idade.setError("Preencha uma idade correta");
                idade.requestFocus();
            } else startActivity(intent);
            reff = FirebaseDatabase.getInstance().getReference().child("Members");
            member.setName(nomeReg);
            member.setData_Nascimento(stridade);

            reff.push().setValue(member);
            Toast.makeText(pagina_registo.this,"data inserted sucessfully",Toast.LENGTH_LONG).show();
        } catch (Exception   EditText idade = (EditText) findViewById(R.id.idadeRegisto);
 e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }

    }
*/
}
