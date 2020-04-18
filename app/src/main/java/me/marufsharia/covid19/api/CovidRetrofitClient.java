package me.marufsharia.covid19.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidRetrofitClient {
    private final static String BASE_URL = "https://covid19-server.chrismichael.now.sh/api/v1/";
    private static Retrofit retrofit;
    
    public static Retrofit getCovidRetrofitClientInstance() {
        if (retrofit == null) ;
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    
}
