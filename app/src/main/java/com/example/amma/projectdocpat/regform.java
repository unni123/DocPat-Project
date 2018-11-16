package com.example.amma.projectdocpat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class regform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] doc = { "Doctor","Dr.Unni Krishnan(Physician)", "Dr.Seethumol K.s(Cardiologist)", "Dr.Mohemmed Asif(Surgeon)", "Dr.Joseph Phillip(ENT)"};
    String spvalue;
    String s1;
    String s2;
    String s4;
    String s5;
    String s6;
    String s7;
    String s8;
    String s9;
    Button submit;

    int nopt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regform);
        submit = findViewById(R.id.sb);


        Spinner sp = findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,doc);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);
       // submit.setVisibility(View.INVISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(regform.this);
                                          alertDialogBuilder.setTitle("Confirm");
                                          alertDialogBuilder.setMessage("Confirm to submit Form");
                                          alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int id) {

                                                  EditText edf = findViewById(R.id.firstnameid);
                                                  EditText edl = findViewById(R.id.lastnmaeid);

                                                  EditText age = findViewById(R.id.ageid);
                                                  EditText dob = findViewById(R.id.dobid);
                                                  EditText hn = findViewById(R.id.houseid);
                                                  EditText det = findViewById(R.id.Detailsid);
                                                  EditText phn = findViewById(R.id.phoneid);

                                                  s1 = edf.getText().toString();
                                                  s2 = edl.getText().toString();
                                                  s4 = age.getText().toString();
                                                  s5 = dob.getText().toString();
                                                  s6 = hn.getText().toString();
                                                  s7 = spvalue;
                                                  s8 = det.getText().toString();
                                                  s9 = phn.getText().toString();

                                              /*validation*/
                                                  if (s1.trim().length()==0)
                                                  {
                                                      String str="[a-zA-Z]";
                                                      if (s1.trim().matches(str)) {
                                                          Toast.makeText(regform.this, "use characters", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }
                                                  else if (s2.trim().length()==0)
                                                  {
                                                      String str="[a-zA-Z]";
                                                      if (s2.trim().matches(str)) {
                                                          Toast.makeText(regform.this, "use characters", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }
                                                  else if (s4.trim().length()==0) {
                                                      String regexStr = "^[0-9]*$";
                                                      if (s4.trim().matches(regexStr)) {
                                                          Toast.makeText(regform.this, "use characters", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }
                                                  else if (s5.trim().length()==0) {

                                                      Toast.makeText(regform.this, "invalid date format", Toast.LENGTH_SHORT).show();
                                                  }
                                                  if (s6.trim().length()==0)
                                                  {
                                                      Toast.makeText(regform.this, "enter address", Toast.LENGTH_SHORT).show();
                                                  }

                                                  if (s8.trim().length()==0)
                                                  {
                                                      Toast.makeText(regform.this, "use characters", Toast.LENGTH_SHORT).show();
                                                  }
                                                  else if (s9.trim().length()==0) {
                                                      String regexStr = "^[0-9]*$";
                                                      if (s9.trim().matches(regexStr)) {
                                                          Toast.makeText(regform.this, "invalid entry,use numbers", Toast.LENGTH_SHORT).show();
                                                      }
                                                  }
                                                  else{


                                                  final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                  final DatabaseReference dbref = database.getReference();
                                                  dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                      @Override
                                                      public void onDataChange(DataSnapshot dataSnapshot) {

                                                          if(dataSnapshot.getChildrenCount()==2)
                                                          {
                                                              dbref.child("ptno").setValue(0);
                                                              nopt=1;
                                                          }
                                                          else
                                                          {
                                                              nopt = Integer.parseInt(String.valueOf(dataSnapshot.child("ptno").getValue()))+1;
                                                          }

                                                          dbref.child("ptno").setValue(nopt);
                                                          DatabaseReference patRef = database.getReference("patient"+ nopt);
                                                          patRef.child("firstname").setValue(s1);
                                                          patRef.child("lastname").setValue(s2);
                                                          patRef.child("age").setValue(s4);
                                                          patRef.child("dob").setValue(s5);
                                                          patRef.child("address").setValue(s6);
                                                          patRef.child("doctor").setValue(s7);
                                                          patRef.child("symptoms").setValue(s8);
                                                          patRef.child("phone_no").setValue(s9);
                                                      }

                                                      @Override
                                                      public void onCancelled(DatabaseError error) {
                                                          Toast.makeText(regform.this, "Unable to fetch value.please restart app.", Toast.LENGTH_SHORT).show();
                                                      }
                                                  });

                                                  Toast.makeText(regform.this, "Booking Submitted.,Consultation time will be informed", Toast.LENGTH_SHORT).show();
                                              }}
                                          });
                                          alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int id) {
                                                  dialog.dismiss();
                                                  Toast.makeText(regform.this, "Booking Cancelled", Toast.LENGTH_SHORT).show();
                                              }
                                          });
                                          AlertDialog dialog = alertDialogBuilder.create();
                                          dialog.show();



                                      }
                                  }
        );
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spvalue=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
