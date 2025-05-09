package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages={
            {"Package 1 : Full Body Checkup", "","","","9000"},
            {"Package 2 : Blood Glucose level", "","","","900"},
            {"Package 3 : Immunity Check", "","","","9700"},
            {"Package 4 : Thyroid Check", "","","","600"},
            {"Package 5 : Covid 19", "","","","9000"}
    };
    private String[] package_details={
            "Blood Glucose Level\n" +
                    "Complete Hemogram\n"+
                    "Hb1c\n"+
                    "LDH Lactate Dehydrogenase , Serum\n"+
                    "Iron Studies\n"+
                    "Kidney Function Test\n"+
                    "Lipid Profile\n"+
                    "Liver Functioning\n",
            "Blood Glucose",
            "Thyroid Profile-Total (T3,T4 & TSH Ultra-sensitive)",
            "Covid 19",
            "Complete Hemogram\n"+
                    "CRP (C Ractive Protein) Quantative ,Serum\n"+
                    "Iron Studies\n"+
                    "Kidney Function test\n" +
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile"

    };
    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart= findViewById(R.id.buttonBMCartCheckout);
        btnBack=findViewById(R.id.buttonHABack);
        listView= findViewById(R.id.listViewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item= new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it= new Intent(LabTestActivity.this,LabTestActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text",packages[i][4]);
                startActivity(it);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LabTestActivity.this,CartActivity.class));
            }
        });


        }
    }
