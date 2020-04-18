package me.marufsharia.covid19.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.World;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;

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
import me.marufsharia.covid19.util.Utils;
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
    
    
    SearchView searchView2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        World.init(this);
        reportList = new ArrayList<>();
        tableList = new ArrayList<>();
        ButterKnife.bind(this);
        
        /*check internet connection*/
        checkInternetConnection();

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        covidDataRecyclerViewAdapter = new CovidDataRecyclerViewAdapter(tableList);
        recyclerView.setAdapter(covidDataRecyclerViewAdapter);
        getCovidData();
        
        
        swipyRefreshLayout.setOnRefreshListener(direction -> {
            getCovidData();
            swipyRefreshLayout.setRefreshing(false);
        });
        
        searchView2 = findViewById(R.id.search_view);
        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            
            @Override
            public boolean onQueryTextChange(String newText) {
                if (covidDataRecyclerViewAdapter != null) {
                    covidDataRecyclerViewAdapter.getFilter().filter(newText);
                }
                
                return true;
            }
        });
        
    }
    
    
    public void getCovidData() {
        if (Utils.isConnected(this)) {
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
                        tableList.remove(tableList.size() - 1);
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
    
    @Override
    protected void onResume() {
        super.onResume();
        checkInternetConnection();
    }
    
    private void checkInternetConnection() {
        if (!Utils.isConnected(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Internet Connection!!!")
                    .setMessage("Please turn on your internet connection.")
                    .setCancelable(false)
                    .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           
                            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
