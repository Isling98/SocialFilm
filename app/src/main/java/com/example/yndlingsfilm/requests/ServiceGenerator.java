package com.example.yndlingsfilm.requests;

import com.example.yndlingsfilm.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    // retrofitbuilder for movieDB api
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);


    public static MovieApi getMovieApi() {
        return movieApi;
    }


    // retrofitbuilder for own restApi
    private static Retrofit.Builder retrofitBuilder2 =
            new Retrofit.Builder().baseUrl(Constants.BASE_URL2).
                    client(UnsafeOkHttpClient.getUnsafeOkHttpClient()).
                    addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit2 = retrofitBuilder2.build();

    private static UserApi userApi = retrofit2.create(UserApi.class);


    public static UserApi getUserApi() {
        return userApi;
    }
}
