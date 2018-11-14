package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class main extends AppCompatActivity {
    private Button B1,B2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         B1= findViewById(R.id.B1);
         B2= findViewById(R.id.B2);
    }
    public void f1(View view)
    {
        Intent i= new Intent(this,doclog.class);
        startActivity(i);
    }
    public void d1(View view)
    {
        Intent i= new Intent(this,regform.class);
        startActivity(i);
    }
}
