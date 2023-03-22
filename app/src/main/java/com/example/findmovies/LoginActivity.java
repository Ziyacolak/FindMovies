package com.example.findmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findmovies.retrofit.Searching;

public class LoginActivity extends AppCompatActivity {
Button loginBtn;
EditText nameTxT,pswText;
Integer counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        loginBtn = findViewById(R.id.button);
        nameTxT = findViewById(R.id.editTextPersonName);
        pswText = findViewById(R.id.editTextPsw);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameTxT.getText().toString().equals("")&&
                        pswText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Giriş Yapılıyor", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);}
               else {
                    Toast.makeText(getApplicationContext(), "Hatalı Giriş", Toast.LENGTH_SHORT).show();
                    nameTxT.setVisibility(View.VISIBLE);
                    nameTxT.setBackgroundColor(Color.RED);
                    counter--;
                    nameTxT.setText(Integer.toString(counter));

                    if (counter==0){
                        loginBtn.setEnabled(false);
                    }
                }}
        });
    }
}