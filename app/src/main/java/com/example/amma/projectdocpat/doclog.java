package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.annotation.NonNull;
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
    String doc_id,entered_pass,doc_pass,doc_name;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),main.class));
    }

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

            if (E1.getText().toString().trim().length()==0)
            {
                E1.setError("Doctor ID is not entered");
                E1.requestFocus();
            }
            else if (E2.getText().toString().trim().length()==0)
            {
                E2.setError("Password is not entered");
                E2.requestFocus();
            }
            else
            {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("docdb");

                doc_id = String.valueOf(E1.getText());
                entered_pass = String.valueOf(E2.getText());

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String[] doc_ids=new String[]{"d1","d2","d3","d4"};
                        for(int i=0;i<doc_ids.length;i++)
                        {
                            if(String.valueOf(dataSnapshot.child(doc_ids[i]).child("id").getValue()).equals(doc_id)) {
                                doc_pass = String.valueOf(dataSnapshot.child(doc_ids[i]).child("password").getValue());
                                doc_name = String.valueOf(dataSnapshot.child(doc_ids[i]).child("name").getValue());
                            }
                        }

                        if(entered_pass.equals(doc_pass)) {

                            Toast.makeText(getApplicationContext(), "Welcome "+doc_name, Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getApplicationContext(),patient_list.class);
                            Bundle B = new Bundle();
                            B.putString("name",doc_name);
                            i.putExtras(B);
                            startActivity(i);

                        }
                        else {

                            Toast.makeText(getApplicationContext(),  "invalid credentials", Toast.LENGTH_SHORT).show();
                            E1.setText("");
                            E2.setText("");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });



            }}



    }


