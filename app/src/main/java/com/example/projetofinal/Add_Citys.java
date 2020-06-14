package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Citys extends AppCompatActivity {
    City city;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__citys);
    }


    public void Adicionar(View view) {
        EditText nome = (EditText) findViewById(R.id.NomeCidade);
        EditText CodigoPostal = (EditText) findViewById(R.id.CodigoPostalCidade);

        final String nomeCidade = nome.getText().toString();
        final String codigoPostal = CodigoPostal.getText().toString();

        city = new City();


        if (nome.length() <= 3) {
            nome.setError("Preencha o nome da Cidade");
            nome.requestFocus();
        } else if(CodigoPostal.length()<=3) {
            CodigoPostal.setError("Preencha um codigo postal correto");
            CodigoPostal.requestFocus();
        }else{

            reff = FirebaseDatabase.getInstance().getReference().child("City");
            city.setName(nomeCidade);
            city.setCodigo_Postal(codigoPostal);

            reff.push().setValue(city);
            Toast.makeText(Add_Citys.this,"data inserted sucessfully",Toast.LENGTH_LONG).show();
        }
    }
}