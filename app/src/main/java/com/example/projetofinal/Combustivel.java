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


public class Combustivel extends AppCompatActivity {
    DatabaseReference reff;
    Bombas bombas;
    Spinner spinner_local;
    Spinner spinner_bombas;
    ValueEventListener listener;
    ArrayAdapter<String> adapter_local;
    ArrayAdapter<String> adapter_bombas;
    ArrayList<String> spinnerDataList_local;
    ArrayList<String> spinnerDataList_bombas;
    DatabaseReference databaseReference_local;
    DatabaseReference databaseReference_bombas;

    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustivel);
        mAuth = FirebaseAuth.getInstance();
        databaseReference_local = FirebaseDatabase.getInstance().getReference("City");
        databaseReference_bombas = FirebaseDatabase.getInstance().getReference("Gasolina");
        spinner_local = (Spinner) findViewById(R.id.spinner2);
        spinner_bombas = (Spinner) findViewById(R.id.spinner3);

        spinnerDataList_local = new ArrayList<>();
        spinnerDataList_bombas = new ArrayList<>();
        adapter_local = new ArrayAdapter<String>(Combustivel.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList_local);
        adapter_bombas = new ArrayAdapter<String>(Combustivel.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList_bombas);
        spinner_local.setAdapter(adapter_local);
        spinner_bombas.setAdapter(adapter_bombas);
        retrieveData_local();
        retrieveData_bombas();
    }

    public void retrieveData_local() {
        databaseReference_local.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList_local.add(item.getValue().toString());
                    Toast.makeText(Combustivel.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
                }
                adapter_local.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void retrieveData_bombas() {
        databaseReference_bombas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList_bombas.add(item.getValue().toString());
                    Toast.makeText(Combustivel.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
                }
                adapter_bombas.notifyDataSetChanged();
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
            final Spinner spinnerLocalidade = (Spinner) findViewById(R.id.spinner2);
            final Spinner spinnerbombas = (Spinner) findViewById(R.id.spinner3);

            EditText ruabomb = (EditText) findViewById(R.id.RuaBombas);
            EditText horariobomb = (EditText) findViewById(R.id.HorarioBombas);


            final String ruaBomb = ruabomb.getText().toString();
            final String horarioBomb = horariobomb.getText().toString();


            int SelectedLocalidade = spinnerLocalidade.getSelectedItemPosition();
            int SelectedBombas = spinnerbombas.getSelectedItemPosition();


            bombas=new Bombas();
            final String SpinnerLocalidade = spinnerLocalidade.getItemAtPosition(SelectedLocalidade).toString();
            final String SpinnerBombas = spinnerbombas.getItemAtPosition(SelectedBombas).toString();

          if(ruaBomb.isEmpty()) {
                ruabomb.setError("Preencha o nome");
                ruabomb.requestFocus();
            }else if(horarioBomb.isEmpty()) {
                horariobomb.setError("Preencha o nome");
                horariobomb.requestFocus();
            }else{
                startActivity(intent);
                reff = FirebaseDatabase.getInstance().getReference().child("Bombas de gasolina");
                bombas.setName(SpinnerBombas);
                bombas.setLocalidade(SpinnerLocalidade);
                bombas.setRua(ruaBomb);
                bombas.setHorario(horarioBomb);
                reff.push().setValue(bombas);
                Toast.makeText(Combustivel.this, "data inserted sucessfully", Toast.LENGTH_LONG).show();
                ruabomb.setText("");
                horariobomb.setText("");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }


    }
}