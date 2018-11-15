package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class display_patient extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient);

        t1= findViewById(R.id.tv1);
        t2= findViewById(R.id.tv2);
        t3= findViewById(R.id.tv3);
        t4= findViewById(R.id.tv4);
        t5= findViewById(R.id.tv5);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        String firstname=b.getString("firstname");
        String lastname=b.getString("lastname");
        String age=b.getString("age");
        String dob=b.getString("dob");
        String address=b.getString("address");

        t1.setText(firstname);
        t2.setText(lastname);
        t3.setText(age);
        t4.setText(dob);
        t5.setText(address);

    }
}
