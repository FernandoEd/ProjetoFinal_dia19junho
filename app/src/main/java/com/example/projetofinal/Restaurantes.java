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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Restaurantes extends AppCompatActivity {
    DatabaseReference reff;
    Restaurante restaurante;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner4);

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Restaurantes.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();
    }

    public void retrieveData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                    Toast.makeText(Restaurantes.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
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
            final Spinner spinnerLocalidade = (Spinner) findViewById(R.id.spinner4);
            EditText nome = (EditText) findViewById(R.id.Nome_restaurante);
            EditText ruaRestaurante = (EditText) findViewById(R.id.Rua_restaurante);
            EditText horarioRestaurante = (EditText) findViewById(R.id.Horario_Restaurante);


            final String ruaRes = ruaRestaurante.getText().toString();
            final String horarioRes = horarioRestaurante.getText().toString();
            final String nomeReg = nome.getText().toString();

            int Selected = spinnerLocalidade.getSelectedItemPosition();

            restaurante= new Restaurante();
            final String SpinnerLocalidade = spinnerLocalidade.getItemAtPosition(Selected).toString();

            if (nomeReg.isEmpty()) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            }else if(ruaRes.isEmpty()) {
                ruaRestaurante.setError("Preencha o nome");
                ruaRestaurante.requestFocus();
            }else if(horarioRes.isEmpty()) {
                horarioRestaurante.setError("Preencha o nome");
                horarioRestaurante.requestFocus();
            }else{
                startActivity(intent);
                reff = FirebaseDatabase.getInstance().getReference().child("Restaurantes");
                restaurante.setName(nomeReg);
                restaurante.setLocalidade(SpinnerLocalidade);
                restaurante.setRuaRestaurante(ruaRes);
                restaurante.setHorarioRestaurante(horarioRes);
                reff.push().setValue(restaurante);
                Toast.makeText(Restaurantes.this, "data inserted sucessfully", Toast.LENGTH_LONG).show();
                nome.setText("");
                ruaRestaurante.setText("");
                horarioRestaurante.setText("");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }


    }
}
