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
import android.widget.Button;
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
    boolean emailOut;
    private FirebaseAuth mAuth;
    

    FirebaseDatabase data;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_bares_read);
        boolean bool = false;
        emailOut = getIntent().getBooleanExtra("name", bool);

        if(!emailOut){
            setTheme(R.style.AppTheme_Others_admin); // (for Custom theme)
            this.setContentView(R.layout.activity_cafe_bares_read);
            MenuItem menuItem= (MenuItem)findViewById(R.id.inserir);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(VerDadosGuardados.this, Cafe_bares.class);
                    startActivity(intent);
                    return true;
                }
            });

        }else {
            setTheme(R.style.AppTheme); // (for Custom theme)
            this.setContentView(R.layout.activity_cafe_bares_read);
        }
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner6);
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(VerDadosGuardados.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("City");
        spinner = (Spinner) findViewById(R.id.spinner6);




        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(VerDadosGuardados.this, R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);



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
                        TextView textViewRua = (TextView) findViewById(R.id.textView3);
                        textViewRua.setText(item.child("horarioCafeBar").getValue().toString());
                        TextView textViewHorario = (TextView) findViewById(R.id.textView4);
                        textViewHorario.setText(item.child("ruaCafeBar").getValue().toString());
                        Toast.makeText(VerDadosGuardados.this, "Data loaded sucessful", Toast.LENGTH_LONG).show();
                    }
                }
                adapter_bar.notifyDataSetChanged();
                spinner_bar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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