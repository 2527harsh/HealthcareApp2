package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dateButton = findViewById(R.id.buttonBMCartDate);
        timeButton = findViewById(R.id.buttonCartTime);
        btnCheckout = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonHABack);
        tvTotal= findViewById(R.id.textViewBMCartTotalCost);
        lst = findViewById(R.id.listViewHA);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        float totalAmount = 0; // Corrected initialization
        ArrayList dbData = db.getCartData(username, "lab");
        Toast.makeText(getApplicationContext(), ""+ dbData, Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
       
        for(int i=0;i<dbData.size();i++){
            String arrData =dbData.get(i).toString();
            String[] strData = arrData.split(Pattern.quote("$"));
            packages[i][0]= strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount=totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total cost :"+totalAmount);
        
        list =new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String >();
            item.put("line1 ", packages[i][0]);
            item.put("line2 ", packages[i][1]);
            item.put("line3 ", packages[i][2]);
            item.put("line4 ", packages[i][3]);
            item.put("line5 ", packages[i][4]);
            item.get( item ); // add
            
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                 new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, LabTestActivity.class));
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it= new Intent(CartActivity.this,LabTestBookActivity.class);
                it.putExtra("price",tvTotal.getText());
                it.putExtra("date", dateButton.getText());
                it.putExtra("time", timeButton.getText());
                startActivity(it);
            }
        });
 
        //timePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            // Do something when date is set
        };

        // Get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog instance
        datePickerDialog = new DatePickerDialog(this, dateSetListener, mYear, mMonth, mDay);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (view, hourOfDay, minute) -> {
            // Do something when time is set
        };

        // Get current time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Create TimePickerDialog instance
        timePickerDialog = new TimePickerDialog(this, timeSetListener, mHour, mMinute, false);
    }
}
