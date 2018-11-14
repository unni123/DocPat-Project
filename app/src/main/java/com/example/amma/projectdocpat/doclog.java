package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doclog extends AppCompatActivity {
    EditText E1,E2;
    Button B3;
    TextView tv1,tv2;
    int f;
    String doc_id,doc_pass,doc_name,doc_dep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doclog);
        B3 = findViewById(R.id.b3);
        E1 = findViewById(R.id.E1);
        E2 = findViewById(R.id.E2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                created();
            }
        });

    }
        public void created( )
        {

            f=0;

            if (E1.getText().toString().trim().length()==0)
            {
                f++;
                E1.setError("Doctor ID is not entered");
                E1.requestFocus();
            }
            else if (E2.getText().toString().trim().length()==0)
            {
                f++;
                E2.setError("Password is not entered");
                E2.requestFocus();
            }
            else
            {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("docdb");

                doc_id = String.valueOf(E1.getText());


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(String.valueOf(dataSnapshot.child("d1").child("id").getValue()).equals(doc_id)) {
                            doc_pass = String.valueOf(dataSnapshot.child("d1").child("password").getValue());
                            doc_name = String.valueOf(dataSnapshot.child("d1").child("name").getValue());
                            doc_dep =  String.valueOf(dataSnapshot.child("d1").child("department").getValue());
                        }
                            else if(String.valueOf(dataSnapshot.child("d2").child("id").getValue()).equals(doc_id)) {
                            doc_pass = String.valueOf(dataSnapshot.child("d2").child("password").getValue());
                            doc_name = String.valueOf(dataSnapshot.child("d2").child("name").getValue());
                            doc_dep =  String.valueOf(dataSnapshot.child("d3").child("department").getValue());
                        }
                            else if(String.valueOf(dataSnapshot.child("d3").child("id").getValue()).equals(doc_id)) {
                            doc_pass = String.valueOf(dataSnapshot.child("d3").child("password").getValue());
                            doc_name = String.valueOf(dataSnapshot.child("d3").child("name").getValue());
                            doc_dep =  String.valueOf(dataSnapshot.child("d3").child("department").getValue());
                        }
                            else if(String.valueOf(dataSnapshot.child("d4").child("id").getValue()).equals(doc_id)) {
                            doc_pass = String.valueOf(dataSnapshot.child("d4").child("password").getValue());
                            doc_name = String.valueOf(dataSnapshot.child("d4").child("name").getValue());
                            doc_dep =  String.valueOf(dataSnapshot.child("d4").child("department").getValue());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

                    if(E2.getText().toString().equals(doc_pass)) {

                    Toast.makeText(this, "Welcome "+doc_name+" of Department "+doc_dep, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(this,display.class);
                    Bundle B = new Bundle();
                    B.putString("name",doc_name);
                    i.putExtras(B);
                    startActivity(i);

                    //E1.setText("");
                   // E2.setText("");
                }
                else {

                    Toast.makeText(this,  "invalid credentials", Toast.LENGTH_SHORT).show();
                }

            }
             }

    }


