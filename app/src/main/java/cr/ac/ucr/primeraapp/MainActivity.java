package cr.ac.ucr.primeraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import cr.ac.ucr.primeraapp.adapters.MainViewPagerAdapter;
import cr.ac.ucr.primeraapp.fragments.ProfileFragment;
import cr.ac.ucr.primeraapp.fragments.ToDoListFragment;
import cr.ac.ucr.primeraapp.utils.AppPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vpPager;
    private BottomNavigationView bottomNavigationMenuView;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vpPager = findViewById(R.id.vp_pager);

        bottomNavigationMenuView = findViewById(R.id.bnv_bottom_menu);

        setupViewPagerListener();
        setupBottomNavViewListener();

        setupViewPager();

     }

    private void setupBottomNavViewListener() {

        bottomNavigationMenuView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.my_task:
                        vpPager.setCurrentItem(0);
                        return true;
                    case R.id.profile:
                        vpPager.setCurrentItem(1);
                        return true;
                    default:
                        return false;
                }

        }
        });



    }

    private void setupViewPagerListener() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                }
                bottomNavigationMenuView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationMenuView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(ToDoListFragment.newInstance());
        fragments.add(ProfileFragment.newInstance());

        //ViewPager <-------> Adapter <-------> ArrayList
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);

        vpPager.setAdapter(mainViewPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        AppPreferences.getInstance(this).clear();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

    }

}