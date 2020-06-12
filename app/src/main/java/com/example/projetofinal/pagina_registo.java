package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;


public class pagina_registo extends AppCompatActivity {
DatabaseReference reff;
Members member;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_registo);
    }



    public void Registo(View view) {
        Intent intent = new Intent(this, paginaInicia.class);
        try {
            EditText nome = (EditText) findViewById(R.id.nomeRegisto);
            EditText idade = (EditText) findViewById(R.id.idadeRegisto);
            EditText telefone = (EditText) findViewById(R.id.telefoneRegisto);
            EditText email = (EditText) findViewById(R.id.emailRegisto);
            EditText localidade = (EditText) findViewById(R.id.localRegisto);
            EditText password = (EditText) findViewById(R.id.passwordRegisto);

            String nomeReg = nome.getText().toString();
            String local = localidade.getText().toString();
            String telefoneReg = telefone.getText().toString();
            String emailReg = email.getText().toString();
            String stridade = idade.getText().toString();
            String passwordReg = password.getText().toString();
                member = new Members();
            int idadefinal = Integer.parseInt(stridade);

            if (nomeReg.length() <= 3) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            } else if ((idadefinal < 0) || (idadefinal > 120) ) {
                idade.setError("Preencha uma idade correta");
                idade.requestFocus();
            } else if (telefoneReg.length() != 9) {
                telefone.setError("Preencha uma numero de telefone correto");
                telefone.requestFocus();
            } else if (local.length() <= 3) {
                localidade.setError("Preencha uma localização correta");
                localidade.requestFocus();
            } else if (passwordReg.length() <= 6) {
                localidade.setError("Password fraca");
                localidade.requestFocus();

            } else {

                startActivity(intent);
                reff = FirebaseDatabase.getInstance().getReference().child("Members");
                member.setName(nomeReg);
                member.setData_Nascimento(stridade);
                member.setTelefone(telefoneReg);
                member.setLocalização(local);
                member.setEmail(emailReg);
                member.setPassword(passwordReg);
                reff.push().setValue(member);
                Toast.makeText(pagina_registo.this,"data inserted sucessfully",Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }



    }


}
