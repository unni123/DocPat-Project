package com.example.amma.projectdocpat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient_list extends AppCompatActivity {

    String[] mobileArray={""};
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String name =b.getString("name");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbref = database.getReference();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               long ptno = (long) dataSnapshot.child("ptno").getValue();
               flag = 0;

                for(int i = 1;i <= ptno;i++) {
                   String doc_name = String.valueOf(dataSnapshot.child("patient" + i).child("doctor").getValue());
                   if (doc_name.equals(name))
                       flag++;
               }

                mobileArray = new String[flag];
                final pat_details[] pt_arr = new pat_details[flag];

                for(int i = 1,j=0;i <= ptno;i++,j++) {
                    String doc_name = String.valueOf(dataSnapshot.child("patient" + i).child("doctor").getValue());
                    if (doc_name.equals(name)) {
                        pt_arr[j] = new pat_details();
                        pt_arr[j].setFirstname(String.valueOf(dataSnapshot.child("patient" + i).child("firstname").getValue()));
                        pt_arr[j].setLastname(String.valueOf(dataSnapshot.child("patient" + i).child("lastname").getValue()));
                        pt_arr[j].setAge(String.valueOf(dataSnapshot.child("patient" + i).child("age").getValue()));
                        pt_arr[j].setDob(String.valueOf(dataSnapshot.child("patient" + i).child("dob").getValue()));
                        pt_arr[j].setAddress(String.valueOf(dataSnapshot.child("patient" + i).child("address").getValue()));
                        pt_arr[j].setSymptoms(String.valueOf(dataSnapshot.child("patient" + i).child("symptoms").getValue()));
                        pt_arr[j].setPhone_no(String.valueOf(dataSnapshot.child("patient" + i).child("phone_no").getValue()));

                        mobileArray[j] = pt_arr[j].getFirstname();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),R.layout.activity_listview, mobileArray);
                ListView listView = findViewById(R.id.mobile_list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        for(int j=0;j<flag;j++)
                        {
                            if(j==i)
                            {
                                Intent intent=new Intent(getApplicationContext(),display_patient.class);
                                Bundle B = new Bundle();
                                B.putString("firstname",pt_arr[j].getFirstname());
                                B.putString("lastname",pt_arr[j].getLastname());
                                B.putString("age",pt_arr[j].getAge());
                                B.putString("dob",pt_arr[j].getDob());
                                B.putString("address",pt_arr[j].getAddress());
                                B.putString("symptoms",pt_arr[j].getSymptoms());
                                B.putString("phone_no",pt_arr[j].getPhone_no());
                                intent.putExtras(B);
                                startActivity(intent);
                            }

                        }
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Unable to fetch value.please restart app.", Toast.LENGTH_SHORT).show();
            }
        });




    }
}

class pat_details
{
    private String firstname;
    private String lastname;
    private String age;
    private String dob;
    private String address;
    private String symptoms;
    private String phone_no;

    pat_details()
    {
        this.firstname="";
        this.lastname="";
        this.age="";
        this.dob="";
        this.address="";
        this.symptoms="";
        this.phone_no="";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
