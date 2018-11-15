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
    TextView t6;
    TextView t7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_patient);

        t1= findViewById(R.id.tv1);
        t2= findViewById(R.id.tv2);
        t3= findViewById(R.id.tv3);
        t4= findViewById(R.id.tv4);
        t5= findViewById(R.id.tv5);
        t6= findViewById(R.id.tv6);
        t7= findViewById(R.id.tv7);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        String firstname=b.getString("firstname");
        String lastname=b.getString("lastname");
        String age=b.getString("age");
        String dob=b.getString("dob");
        String address=b.getString("address");
        String symptoms=b.getString("symptoms");
        String phone_no=b.getString("phone_no");

        t1.setText(firstname);
        t2.setText(lastname);
        t3.setText(age);
        t4.setText(dob);
        t5.setText(address);
        t5.setText(symptoms);
        t5.setText(phone_no);
    }
}
