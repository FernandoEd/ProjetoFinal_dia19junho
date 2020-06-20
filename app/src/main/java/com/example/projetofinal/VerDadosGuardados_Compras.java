package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VerDadosGuardados_Compras extends AppCompatActivity {
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
    boolean emailOut;
    private FirebaseAuth mAuth;
    DatabaseReference reff;

    FirebaseDatabase data;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.inserir:
                Intent intent = new Intent(VerDadosGuardados_Compras.this, Tabela_Compras.class);
                startActivity(intent);
                break;
        }
        return true;
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean bool = false;
        emailOut = getIntent().getBooleanExtra("name", bool);

        if (!emailOut) {
            setTheme(R.style.AppTheme_Others_admin); // (for Custom theme)
            this.setContentView(R.layout.activity_ver_dados_guardados__compras);
        } else {
            setTheme(R.style.AppTheme); // (for Custom theme)
            this.setContentView(R.layout.activity_ver_dados_guardados__compras);
        }
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner8);
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(VerDadosGuardados_Compras.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return true;
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

                        selected = parent.getItemAtPosition(position).toString();


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

        spinner_bar = (Spinner) findViewById(R.id.spinner9);
        spinnerDataList_bar = new ArrayList<>();
        adapter_bar = new ArrayAdapter<String>(VerDadosGuardados_Compras.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList_bar);
        spinner_bar.setAdapter(adapter_bar);
        databaseReference_bar = FirebaseDatabase.getInstance().getReference("Local");
        Toast.makeText(VerDadosGuardados_Compras.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
        databaseReference_bar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                for (final DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.child("localidade").getValue(String.class).equals(selected)) {
                        spinnerDataList_bar.add((String) item.child("name").getValue().toString());
                        spinner_bar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                selected2 = parent.getItemAtPosition(position).toString();
                              }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                TextView textView = (TextView) findViewById(R.id.textView2);
                                if(item.child("name").getValue().equals(selected2)) {
                                    textView.setText("Ola");

                                }else textView.setText("");
                            }
                        });

                    }
                }

                adapter_bar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

}}
