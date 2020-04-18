package me.marufsharia.covid19.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.blongho.country_data.World;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.marufsharia.covid19.R;
import me.marufsharia.covid19.adapters.CovidDataRecyclerViewAdapter;
import me.marufsharia.covid19.api.CovidRetrofitClient;
import me.marufsharia.covid19.interfaces.CovidApiInterface;
import me.marufsharia.covid19.models.CovidData;
import me.marufsharia.covid19.models.Report;
import me.marufsharia.covid19.models.Table;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CovidDataRecyclerViewAdapter covidDataRecyclerViewAdapter;
    ProgressDialog progressDialog;
    List<Table> tableList;
    List<Report> reportList;
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    @BindView(R.id.covid_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipyrefreshlayout)
    SwipyRefreshLayout swipyRefreshLayout;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        World.init(this);
        reportList = new ArrayList<>();
        tableList = new ArrayList<>();
        ButterKnife.bind(this);
        
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Covid19 Tracker");
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        covidDataRecyclerViewAdapter = new CovidDataRecyclerViewAdapter(tableList);
        recyclerView.setAdapter(covidDataRecyclerViewAdapter);
        getCovidData();
      
        
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getCovidData();
                swipyRefreshLayout.setRefreshing(false);
            }
        });
        
        
    }
    
    
    public void getCovidData() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        
        CovidApiInterface covidApiInterface = CovidRetrofitClient.getCovidRetrofitClientInstance()
                .create(CovidApiInterface.class);
        
        Call<CovidData> listCall = covidApiInterface.getAllCovidData();
        listCall.enqueue(new Callback<CovidData>() {
            @Override
            public void onResponse(Call<CovidData> call, Response<CovidData> response) {
//                Log.d(TAG, "calling url :  " + response.raw().request().url());
                if (response.body() != null) {
                    tableList.clear();
                    tableList.addAll(response.body().getReports().get(0).getTable().get(0));
                    tableList.remove(0);
                    tableList.remove(tableList.size()-1);
                    progressDialog.dismiss();
                  
                    covidDataRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
            
            @Override
            public void onFailure(Call<CovidData> call, Throwable t) {
                Log.d(TAG, "onFailure " + t.getMessage());
            }
        });
    }
}
