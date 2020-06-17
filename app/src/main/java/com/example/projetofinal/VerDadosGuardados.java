package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VerDadosGuardados extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner_bar;
    String selected;
    String selected2;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter_bar;
    ArrayList<String> spinnerDataList;
    ArrayList<String> spinnerDataList_bar;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference_bar;

    private FirebaseAuth mAuth;

    FirebaseDatabase data;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dados_guardados);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner6);




        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(VerDadosGuardados.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();






    }

    public void retrieveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int position = 0;
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.getValue().toString());
                }

                adapter.notifyDataSetChanged();


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView textView=(TextView) findViewById(R.id.textView3);
                        selected = parent.getItemAtPosition(position).toString();
                        textView.setText(selected);

                    retrieveData_bar();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }

    public void retrieveData_bar() {

        spinner_bar = (Spinner) findViewById(R.id.spinner7);
        spinnerDataList_bar = new ArrayList<>();
        adapter_bar = new ArrayAdapter<String>(VerDadosGuardados.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList_bar);
        spinner_bar.setAdapter(adapter_bar);
        databaseReference_bar = FirebaseDatabase.getInstance().getReference("Cafes e Bares");
        Toast.makeText(VerDadosGuardados.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
        databaseReference_bar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    if(item.child("localidade").getValue(String.class).equals(selected)) {
                        spinnerDataList_bar.add((String) item.child("name").getValue().toString());
                        Toast.makeText(VerDadosGuardados.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
                    }
                }
                adapter_bar.notifyDataSetChanged();
                spinner_bar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TextView textView=(TextView) findViewById(R.id.textView4);
                        selected2 = parent.getItemAtPosition(position).toString();
                        textView.setText(selected2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }

}