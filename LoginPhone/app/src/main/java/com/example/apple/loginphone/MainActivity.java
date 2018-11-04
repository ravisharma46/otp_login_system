package com.example.apple.loginphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMobile;
     private Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMobile= (EditText) findViewById(R.id.editTextMobile);
        button= (Button) findViewById(R.id.buttonContinue);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile=editTextMobile.getText().toString().trim();
                if(mobile.isEmpty() || mobile.length()<10 || mobile.length()>10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent=new Intent(MainActivity.this,VerifyPhoneActivity.class);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent= new Intent(this,ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            Toast.makeText(MainActivity.this,"Already In",Toast.LENGTH_SHORT).show();
        }
    }
}







