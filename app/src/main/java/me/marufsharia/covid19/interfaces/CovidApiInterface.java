package me.marufsharia.covid19.interfaces;

import me.marufsharia.covid19.models.CovidData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApiInterface {
    @GET("AllReports")
    Call<CovidData> getAllCovidData();
}
