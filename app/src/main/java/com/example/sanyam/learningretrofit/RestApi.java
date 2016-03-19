package com.example.sanyam.learningretrofit;

import com.example.sanyam.learningretrofit.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sanyam on 13/3/16.
 */
public interface RestApi {
    @GET("/data/2.5/weather?q=London,uk&appid=92cad3e946347996111bcd6c270257f8")
    Call<Example> getWheatherReport();
}
