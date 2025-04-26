package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Harsh Dubey", "Hospital Address : Charoda","Exp : 12yrs", "Mobile No : 12345678910","500" },
                    {"Doctor Name : Raj Dubey", "Hospital Address : Pune","Exp : 10yrs", "Mobile No : 92345678910","1100" },
                    {"Doctor Name : Karsh Dubey", "Hospital Address : Delhi","Exp : 9yrs", "Mobile No : 82345678910","1200" },
                    {"Doctor Name : Sk Dubey", "Hospital Address : Banglore","Exp : 8yrs", "Mobile No : 72345678910","4400" }

            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Ankur Sharma", "Hospital Address : Mumbai","Exp : 6yrs", "Mobile No : 62345678910","700" },
                    {"Doctor Name : Priya Patel", "Hospital Address : Ahmedabad","Exp : 11yrs", "Mobile No : 52345678910","900" },
                    {"Doctor Name : Rahul Singh", "Hospital Address : Kolkata","Exp : 8yrs", "Mobile No : 42345678910","1500" },
                    {"Doctor Name : Nisha Gupta", "Hospital Address : Chennai","Exp : 14yrs", "Mobile No : 32345678910","2000" }
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Rakesh Kumar", "Hospital Address : Hyderabad","Exp : 9yrs", "Mobile No : 72345678910","1200" },
                    {"Doctor Name : Shreya Sharma", "Hospital Address : Jaipur","Exp : 7yrs", "Mobile No : 82345678910","800" },
                    {"Doctor Name : Manoj Kumar", "Hospital Address : Lucknow","Exp : 10yrs", "Mobile No : 92345678910","1100" },
                    {"Doctor Name : Sonali Gupta", "Hospital Address : Bhopal","Exp : 12yrs", "Mobile No : 12345678910","1000" }
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Ajay Kumar", "Hospital Address : Patna","Exp : 6yrs", "Mobile No : 72345678914","2500" },
                    {"Doctor Name : Preeti Singh", "Hospital Address : Bhopal","Exp : 10yrs", "Mobile No : 82345678914","2600" },
                    {"Doctor Name : Vikas Sharma", "Hospital Address : Pune","Exp : 7yrs", "Mobile No : 92345678914","2700" },
                    {"Doctor Name : Anjali Gupta", "Hospital Address : Raipur","Exp : 11yrs", "Mobile No : 12345678914","2800" }
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Deepak Gupta", "Hospital Address : Nagpur","Exp : 8yrs", "Mobile No : 72345678913","2100" },
                    {"Doctor Name : Swati Patel", "Hospital Address : Ahmedabad","Exp : 11yrs", "Mobile No : 82345678913","2200" },
                    {"Doctor Name : Rohit Sharma", "Hospital Address : Lucknow","Exp : 9yrs", "Mobile No : 92345678913","2300" },
                    {"Doctor Name : Komal Gupta", "Hospital Address : Jaipur","Exp : 14yrs", "Mobile No : 12345678913","2400" }
            };
    TextView tv;
    Button btn;
    String [][] doctor_details = {};
    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewHATitle);
        btn=findViewById(R.id.buttonDDBack);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietitian")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
        list =new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item= new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5" ,"consultant Fess"+doctor_details[i][4]+"/-");
            list.add(item);


        }
        sa=new SimpleAdapter(getApplicationContext(),list,R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e });
        ListView lst= findViewById(R.id.listViewHA);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }
}