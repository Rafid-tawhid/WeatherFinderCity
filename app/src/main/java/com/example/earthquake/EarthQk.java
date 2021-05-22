package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthquake.pojo.Feature;
import com.example.earthquake.pojo.Geometry;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthQk extends AppCompatActivity {
    private String formatedDate;
    private long timeSnap;
    private int selectedMonth;
    private int selectedYear;
    EditText e1,e2;
    TextView t1,t2,t3;
    Button buttonn;
    String a="2014-01-01";
    String b="2014-01-02";
    String c="geojson";
    String url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_qk);

        t3=findViewById(R.id.editTextTextPersonName3);
        t2=findViewById(R.id.editTextTextPersonName2);
        t1=findViewById(R.id.textView);
        buttonn=findViewById(R.id.button2);


        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startdate();


            }

        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enddate();

            }
        });


    }
    private DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            final Calendar calendar=Calendar.getInstance();
            calendar.set(year,month,dayOfMonth);
            final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            formatedDate= sdf.format(calendar.getTime());
            timeSnap=calendar.getTimeInMillis();
            selectedMonth=calendar.get(Calendar.MONTH);
            selectedYear=calendar.get(Calendar.YEAR);


//           final Date currentDate=new Date();
//           final Date selectDate=new Date(timeSnap);

            final long currentDate=System.currentTimeMillis();
            final long periodSeconds = (timeSnap - currentDate) / 1000;
            final long elapsedDays = periodSeconds / 60 / 60 / 24;


            final Bundle bundle=new Bundle();
            bundle.putString("rem", String.valueOf(elapsedDays));


        }
    };

    private void startdate() {

        final Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(EarthQk.this,listener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();
        t2.setText(formatedDate);

    }
    private void enddate() {

        final Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(EarthQk.this,listener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();
        t3.setText(formatedDate);

    }


    public void earthquack(View view) {


        Retrofit retrofit2= new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EarthquakeInterface myapi2=retrofit2.create(EarthquakeInterface.class);
        Call<Example2> exampleCall2=myapi2.getEarthquake(c,a,b);
        exampleCall2.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {

                Example2 mydt=response.body();

                Feature feature=mydt.getFeature();


                String tm=feature.getId();
                t1.setText(String.valueOf(tm));
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {

            }
        });
    }

    }


