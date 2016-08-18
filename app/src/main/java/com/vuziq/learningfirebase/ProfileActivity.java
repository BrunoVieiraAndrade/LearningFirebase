package com.vuziq.learningfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userName = (TextView) findViewById(R.id.userName);
        onIntentReceived();
    }

    private void onIntentReceived() {
        Bundle extras  = getIntent().getExtras();
        if(extras.containsKey("user")){
            FirebaseUser user = extras.getParcelable("user");
            initView(user);
        }
    }

    private void initView(FirebaseUser user) {
        userName.setText(user.getDisplayName());
    }
}
