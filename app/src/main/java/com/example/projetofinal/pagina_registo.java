package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class pagina_registo extends AppCompatActivity {
    DatabaseReference reff;
    Members member;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_registo);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner);

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(pagina_registo.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();
    }

    public void retrieveData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                    Toast.makeText(pagina_registo.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void Registo(View view) {
        final Intent intent = new Intent(this, paginaInicia.class);
        try {
            final Spinner spinnerLocalidade = (Spinner) findViewById(R.id.spinner);
            EditText nome = (EditText) findViewById(R.id.nomeRegisto);
            EditText idade = (EditText) findViewById(R.id.idadeRegisto);
            EditText telefone = (EditText) findViewById(R.id.telefoneRegisto);
            EditText email = (EditText) findViewById(R.id.emailRegisto);

            int Selected = spinnerLocalidade.getSelectedItemPosition();
            EditText password = (EditText) findViewById(R.id.passwordRegisto);
            final String nomeReg = nome.getText().toString();
            final String SpinnerLocalidade = spinnerLocalidade.getItemAtPosition(Selected).toString();
            final String telefoneReg = telefone.getText().toString();
            final String emailReg = email.getText().toString();
            final String stridade = idade.getText().toString();
            final String passwordReg = password.getText().toString();
            member = new Members();
            int idadefinal = Integer.parseInt(stridade);

            if (nomeReg.length() <= 3) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            } else if ((idadefinal < 0) || (idadefinal > 120)) {
                idade.setError("Preencha uma idade correta");
                idade.requestFocus();
            } else if (telefoneReg.length() != 9) {
                telefone.setError("Preencha uma numero de telefone correto");
                telefone.requestFocus();
            } else if (passwordReg.length() <= 6) {
                password.setError("Password fraca");
                password.requestFocus();
            } else {

                mAuth.createUserWithEmailAndPassword(emailReg, passwordReg)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(intent);
                                    reff = FirebaseDatabase.getInstance().getReference().child("Members");
                                    member.setName(nomeReg);
                                    member.setData_Nascimento(stridade);
                                    member.setTelefone(telefoneReg);
                                    member.setLocalização(SpinnerLocalidade);
                                    member.setEmail(emailReg);
                                    member.setPassword(passwordReg);
                                    reff.push().setValue(member);
                                    Toast.makeText(pagina_registo.this, "data inserted sucessfully", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(pagina_registo.this, "Email already registed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }

        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }


    }
}
