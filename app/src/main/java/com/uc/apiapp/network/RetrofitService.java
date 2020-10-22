package com.uc.apiapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.uc.apiapp.util.Constants.BASE_URL;

public class RetrofitService {
    private static Retrofit retrofit;
    private static RetrofitService retrofitService;
    public static  <S> S createService(Class<S> serviceClass){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }
        return retrofitService;
    }
}
