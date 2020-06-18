package com.example.projetofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    DatabaseReference reff;
    Members member;

boolean change =false;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(MainActivity.this, "Firebse connection Success", Toast.LENGTH_LONG).show();


    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void Confirmar(View view) {

        final Intent intent = new Intent(this,paginaInicia.class);
        try {
        final EditText email = (EditText) findViewById(R.id.Login);
        EditText password = (EditText) findViewById(R.id.Password);

        final String emailReg = email.getText().toString();
        final String passwordReg = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailReg, passwordReg)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                                  FirebaseUser user = mAuth.getCurrentUser();
                                  change=true;
                                  intent.putExtra("name",change);
                                  startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Email not found, please register your email",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // ...
                    }
                });
        } catch (Exception e) {
            Toast.makeText(this, "Introduza todos os dados corretamente", Toast.LENGTH_SHORT).show();
        }
    }
    public void enviarParaRegisto(View view) {
        Intent intent = new Intent(this,pagina_registo.class);
        startActivity(intent);
    }
}

