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


public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages={
            {
                "Uprise-30 10000IU Capsule","","","","50"
            },
            {
                "HealthVit Chromium Picolinate 200mcg Capsule","","","","305"
            },
            {"vitamin B Comples Capsule","","","","448"},
            {"Inlife vitamin E Wheat Germ Oil Capsule","","","","539"},
            {"Dolo 650 Tablet","","","","30"},
            {"Crocin 650 Advace Tablet","","","","50"},
            {"Strepsil Medicate Lozenges for sore Throat","","","","40"},
            {"Tata 1,g Calcium + vitamin D3","","","","30"},
            {"Feronia -XT Tablet","","","","130"},
    };
    private String[] package_details={
            "Building and keeping the bones & teeth strong\n"+
                    "Reducing fatigue and muscular pain\n"+
                    "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that play important role in the helping insulin regulation",
            "Provide relief  from vitamin B deficiency\n" +
                    "hlps in red blood cell formation\n"+
                    "maintains healthy nervous system",
            "It provide health as well skin benefits\n"+
                    "reduce skin blemish\n"+
                    "safeguard skin from UV rays",
            "Dolo 650 relievs pain pan and fever",
            "Helps relief from fever and bring down temperature\n"+
                    "Suitable for people with hearth condition ",
            "Relieves symptoms of bacterial throat\n"+
                    "Provide a warm feeling over throat",
            "Reduce risk of calcium deficiency,rickets\n"+
                    "Promotes mobility of joints",
            "elps to reduce iron deficiency due to chronic blood loss"
    };

    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoTOCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnGoTOCart=findViewById(R.id.buttonBMDAddToCart);

        btnGoTOCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
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
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it= new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });

    }

}