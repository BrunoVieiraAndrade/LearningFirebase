package com.vuziq.learningfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEditText;
    EditText passwordEditText;
    Button logInButton;
    TextView signUpTextView;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        emailEditText = (EditText) findViewById(R.id.loginEmailEditText);
        passwordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        logInButton = (Button) findViewById(R.id.loginButton);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        logInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == logInButton){
            progressDialog.setMessage("Logging in...");
            progressDialog.show();
            logIn();
        }else if(view == signUpTextView){
            goToRegistration();
        }
    }

    private void goToRegistration() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    private void logIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            FirebaseUser user = task.getResult().getUser();
                            goToProfileActivity(user);
                        }else {
                            Snackbar.make(findViewById(android.R.id.content), task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void goToProfileActivity(FirebaseUser user) {
        startActivity(new Intent(this, ProfileActivity.class).putExtra("user", (Serializable) user));
        finish();
    }
}