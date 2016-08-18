package com.vuziq.learningfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vuziq.learningfirebase.utils.ActivityUtils;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button button;
    EditText emailEditText;
    FirebaseAuth firebaseAuth;
    EditText passwordEditText;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        emailEditText = (EditText) findViewById(R.id.registerEmailEditText);
        passwordEditText = (EditText) findViewById(R.id.registerPasswordEditText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                progressDialog.setMessage("Registering...");
                progressDialog.show();
                register();
                ActivityUtils.hideKeyboard(this);
                break;
            case R.id.loginTextView:
                goToLogin();
                break;
            default:
        }

    }

    private void goToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

    private void register() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
        startActivity(new Intent(this, ProfileActivity.class).putExtra("user", (Parcelable) user));
        finish();
    }
}
