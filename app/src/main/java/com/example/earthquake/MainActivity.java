package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthquake.pojo.CurrentEarthquakeResponse;
import com.example.earthquake.pojo.Metadata;
import com.example.earthquake.weatherpojo.Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText Et;
    private Context context;
    private TextView Tv,tv2,tv3,t4,t5,t6,feelTv,status;
    private Button button,btn;
    String url="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apiKey="ffaba3882aba15796550b071b87d254e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Et=findViewById(R.id.editTextTextPersonName);

        Tv=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);
        t6=findViewById(R.id.textView6);
        feelTv=findViewById(R.id.feelTv);
        status=findViewById(R.id.feel);
        button=findViewById(R.id.button);






    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.button3:
                Intent i = new Intent(getApplicationContext(),EarthQk.class);
                startActivity(i);
                return true;
            case R.id.refresh:
                Et.setText("");
                Tv.setText("");
                tv2.setText("");
                tv3.setText("");
                status.setText("");
                t4.setVisibility(View.GONE);
                t5.setVisibility(View.GONE);
                t6.setVisibility(View.GONE);
                feelTv.setVisibility(View.GONE);

            case R.id.info:

                Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,Et.getText().toString());
                startActivity(intent);

//                Uri uri=Uri.parse("https://www.google.com/#q="+Et.getText().toString());
//                Intent intent1=new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent1);


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getweather(View view) {


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherapi myapi=retrofit.create(weatherapi.class);
        Call<Example> exampleCall=myapi.getweather(Et.getText().toString().trim(),apiKey);

        exampleCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {



                Example mydata=response.body();
                Main main=mydata.getMain();

                if (call==null)
                {
                    Toast.makeText(context, "Enter Valid City Name", Toast.LENGTH_SHORT).show();
                }
                t4.setVisibility(view.getVisibility());
                t5.setVisibility(view.getVisibility());
                t6.setVisibility(view.getVisibility());
                feelTv.setVisibility(view.getVisibility());
                Double t=main.getTemp();
                Integer tw=main.getHumidity();
                Integer ta=main.getPressure();
                Double feel=main.getFeelsLike();



                Integer temp=(int) (t-273.15);
                Tv.setText(temp+"C");
                tv2.setText(ta +"Pa");
                tv3.setText(String.valueOf(tw)+"Km/h");
                status.setText(feel+"");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });



    }

}