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


public class Tabela_Compras extends AppCompatActivity {
    DatabaseReference reff;
    Local local;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela__compras_inserir);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner_registo_shopping);

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Tabela_Compras.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();
    }

    public void retrieveData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                    Toast.makeText(Tabela_Compras.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
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
            final Spinner spinnerLocalidade = (Spinner) findViewById(R.id.spinner_registo_shopping);
            EditText nome = (EditText) findViewById(R.id.Nome_localidade);
            EditText ruaShopping = (EditText) findViewById(R.id.Rua_Shopping);
            EditText horarioShopping = (EditText) findViewById(R.id.HorarioFuncionamento);


            final String ruaShopp = ruaShopping.getText().toString();
            final String horarioShopp = horarioShopping.getText().toString();
            final String nomeReg = nome.getText().toString();

            int Selected = spinnerLocalidade.getSelectedItemPosition();

            local=new Local();
            final String SpinnerLocalidade = spinnerLocalidade.getItemAtPosition(Selected).toString();

            if (nomeReg.isEmpty()) {
                nome.setError("Preencha o nome");
                nome.requestFocus();
            }else if(ruaShopp.isEmpty()) {
                ruaShopping.setError("Preencha o nome");
                ruaShopping.requestFocus();
            }else if(horarioShopp.isEmpty()) {
                horarioShopping.setError("Preencha o nome");
                horarioShopping.requestFocus();
            }else{
                startActivity(intent);
                reff = FirebaseDatabase.getInstance().getReference().child("Local");
                local.setName(nomeReg);
                local.setLocalidade(SpinnerLocalidade);
                local.setRuaShopping(ruaShopp);
                local.setHorarioShopping(horarioShopp);
                reff.push().setValue(local);
                Toast.makeText(Tabela_Compras.this, "data inserted sucessfully", Toast.LENGTH_LONG).show();
                nome.setText("");
                ruaShopping.setText("");
                horarioShopping.setText("");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }


    }
}