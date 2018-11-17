package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class display_patient extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    Button back;
    Button submit;
    String doc_name;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),patient_list.class);
        Bundle b =new Bundle();
        b.putString("name",doc_name);
        i.putExtras(b);
        startActivity(i);
    }

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
        back=findViewById(R.id.b1);
        submit=findViewById(R.id.b2);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        final String firstname=b.getString("firstname");
        String lastname=b.getString("lastname");
        String age=b.getString("age");
        String dob=b.getString("dob");
        String address=b.getString("address");
        String symptoms=b.getString("symptoms");
        final String phone_no=b.getString("phone_no");


        doc_name=b.getString("doc_name");

        t1.setText(firstname);
        t2.setText(lastname);
        t3.setText(age);
        t4.setText(dob);
        t5.setText(address);
        t6.setText(symptoms);
        t7.setText(phone_no);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),patient_list.class);
                Bundle b =new Bundle();
                b.putString("name",doc_name);
                i.putExtras(b);
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference dbref = database.getReference();

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        long ptno = (long) dataSnapshot.child("ptno").getValue();
                        for(int i = 1;i <= ptno;i++) {
                            String phone =String.valueOf(dataSnapshot.child("patient" + i).child("phone_no").getValue());
                            if(phone.equals(phone_no))
                            dbref.child("patient"+i).removeValue();
                        }

                        Toast.makeText(display_patient.this, "Patient removed from list", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),patient_list.class);
                        Bundle B = new Bundle();
                        B.putString("name",doc_name);
                        i.putExtras(B);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Unable to fetch value.please restart app.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
