package me.marufsharia.covid19.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import com.blongho.country_data.World;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.marufsharia.covid19.R;
import me.marufsharia.covid19.fragments.TodayFragment;
import me.marufsharia.covid19.fragments.YesterdayFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener  {
    private static final String TAG = "MainActivity";
    
    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        World.init(this);
        ButterKnife.bind(this);
        
        //toolbar set
        setSupportActionBar(toolbar);
        
        //drawer set
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TodayFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);
    
    
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    
        // bottomNavigationView.setSelectedItemId(R.id.mnu_mosque_list);
        Menu bottomNavigationMenu = bottomNavigationView.getMenu();
        bottomNavigationMenu.performIdentifierAction(R.id.bnav_today, 0);
    
    
    }
    
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.bnav_today:
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TodayFragment()).commit();
                break;
            case R.id.bnav_yesterday:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new YesterdayFragment()).commit();
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_facebook:
                String url ="https://m.facebook.com/marufsharia";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }
    
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
