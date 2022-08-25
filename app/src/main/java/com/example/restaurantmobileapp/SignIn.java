package com.example.restaurantmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText Email, Password;
    String email, password;
    Intent outIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.loginEmail);
        Password = findViewById(R.id.loginPassword);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.signup).setOnClickListener(this);

    }

    private void login(){
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        if(email.isEmpty()){
            Email.setError("Email is nor correct");
            Email.requestFocus();
        }else if(password.isEmpty()){
            Password.setError("Password is nor correct");
            Password.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String key = task.getResult().getUser().getUid();
                        if(task.isSuccessful()){
                            finish();
                            Intent nIntent;
                            Toast.makeText(SignIn.this, "email\\ "+email + " pass\\"+password, Toast.LENGTH_SHORT).show();

                            if (email.equals("admin@gmail.com") && password.equals("admin12")){
                               nIntent = new Intent(SignIn.this, AdminPage.class);
                           }else {
                               nIntent = new Intent(SignIn.this, Home.class);
                           }
                            //intent = new Intent(SignIn.this, AdminPage.class);
                            nIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(nIntent);
                        }else{
                            Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            );
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3:
                login();
                break;
            case R.id.signup:
                startActivity(new Intent(this, SignUp.class));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
// ON Start
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(mAuth.getCurrentUser() != null){
//            finish();
//            Toast.makeText(this, mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, Home.class));
//        }
//    }

}