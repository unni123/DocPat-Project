package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent in = getIntent();
        Bundle bu= in.getExtras();
        String name =bu.getString("name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();


    }
}
