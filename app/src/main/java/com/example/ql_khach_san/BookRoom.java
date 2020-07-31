package com.example.ql_khach_san;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ql_khach_san.model.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BookRoom extends AppCompatActivity {
    String hourIn, dateIn, timeIn, hourOut, dateOut, timeOut;
    int type, sum;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        db = new DatabaseHelper(this);

        final EditText edName;
        final TextView tvTimeIn, tvTimeOut, tvSum;
        Button btnHourIn, btnHourOut, btnDateIn, btnDateOut, btnBook;
        Spinner spinner = findViewById(R.id.spinnerType);

        edName = findViewById(R.id.edName);
        tvTimeIn = findViewById(R.id.tvTimeIn);
        tvTimeOut = findViewById(R.id.tvTimeOut);
        tvSum = findViewById(R.id.tvSum);
        btnHourIn = findViewById(R.id.btnHourIn);
        btnHourOut = findViewById(R.id.btnHourOut);
        btnDateIn = findViewById(R.id.btnDateIn);
        btnDateOut = findViewById(R.id.btnDateOut);
        btnBook = findViewById(R.id.btnBook);

        String[] listType = {"PHONG THUONG", "PHONG VIP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listType);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                    type=0;
                else
                    type=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnHourIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BookRoom.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        hourIn = hour + ":" + minute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        btnDateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(BookRoom.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateIn =  day+"/"+(month+1)+"/"+year;
                        timeIn = hourIn + " " + dateIn;
                        tvTimeIn.setText(timeIn);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        btnHourOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BookRoom.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        hourOut = hour + ":" + minute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        btnDateOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(BookRoom.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateOut =  day+"/"+(month+1)+"/"+year;
                        timeOut = hourOut + " " + dateOut;
                        tvTimeOut.setText(timeOut);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MM/yyyy");

                Date date1=null, date2=null;
                try {
                    date1 = format.parse(timeIn);
                    date2 = format.parse(timeOut);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long diff = (date2.getTime() - date1.getTime())/(60*60*1000);


                if(type==0){
                    sum = (int)diff*100000;
                }
                else{
                    sum = (int)diff*200000;
                }

                tvSum.setText("TONG TIEN: " + sum);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booking booking = new Booking(edName.getText().toString(), timeIn, timeOut, type, sum);
                db.addBooking(booking);
                Toast.makeText(getBaseContext(), "THUE PHONG THANH CONG", Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}
