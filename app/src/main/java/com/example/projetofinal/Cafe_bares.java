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


public class Cafe_bares extends AppCompatActivity {
    String spinner1;
    DatabaseReference reff;
   CafeBar cafeBar;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_bares);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner5);

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Cafe_bares.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();
    }

    public void retrieveData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                    Toast.makeText(Cafe_bares.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
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
            final Spinner spinnerLocalidade = (Spinner) findViewById(R.id.spinner5);
            EditText nome = (EditText) findViewById(R.id.nome_cafe_bar);
            EditText ruaBar = (EditText) findViewById(R.id.rua_cafe_bar);
            EditText horarioBar = (EditText) findViewById(R.id.horario_cafe_bar);


            final String ruaShopp = ruaBar.getText().toString();
            final String horarioShopp = horarioBar.getText().toString();
            final String nomeReg = nome.getText().toString();

            int Selected = spinnerLocalidade.getSelectedItemPosition();

            cafeBar=new CafeBar();
            final String SpinnerLocalidade = spinnerLocalidade.getItemAtPosition(Selected).toString();

            if (nomeReg.isEmpty()) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            }else if(ruaShopp.isEmpty()) {
                ruaBar.setError("Preencha o nome");
                ruaBar.requestFocus();
            }else if(horarioShopp.isEmpty()) {
                horarioBar.setError("Preencha o nome");
                horarioBar.requestFocus();
            }else{
                startActivity(intent);
                reff = FirebaseDatabase.getInstance().getReference().child("Cafes e Bares");
                cafeBar.setName(nomeReg);
                cafeBar.setLocalidade(SpinnerLocalidade);
                cafeBar.setRuaCafeBar(ruaShopp);
                cafeBar.setHorarioCafeBar(horarioShopp);
                reff.push().setValue(cafeBar);
                Toast.makeText(Cafe_bares.this, "data inserted sucessfully", Toast.LENGTH_LONG).show();
                nome.setText("");
                ruaBar.setText("");
                horarioBar.setText("");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }


    }
}