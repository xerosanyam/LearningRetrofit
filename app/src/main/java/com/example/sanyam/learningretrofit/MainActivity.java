package com.example.sanyam.learningretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.sanyam.learningretrofit.Model.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    String url = "http://api.openweathermap.org";
    TextView txt_city, txt_status, txt_humidity, txt_pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_humidity = (TextView) findViewById(R.id.txt_humidity);
        txt_pressure = (TextView) findViewById(R.id.txt_press);
        Log.e("here", "1");
        getReport();
        Log.e("here", "2");
    }

    void getReport() {
        Log.e("here", "3");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);

        Call<Example> call = service.getWheatherReport();
        Log.e("here", "00");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                try {
                    String city = response.body().getName();
                    String status = response.body().getWeather().get(0).getDescription();
                    String humidity = response.body().getMain().getHumidity().toString();
                    String pressure = response.body().getMain().getPressure().toString();
                    Log.e("cit", city);
                    Log.e("getting repport", "ahaha");
                    txt_city.setText("city  :  " + city);
                    txt_status.setText("status  :  " + status);
                    txt_humidity.setText("humidity  : " + humidity);
                    txt_pressure.setText("pressure  :  " + pressure);
                    Log.e("here", "4");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
