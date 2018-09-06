package com.framgia.moviedb_27.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private MainViewModel mMainViewModel;
    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        setListenerForMenu();
    }

    private void setListenerForMenu() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.button_genre:
                                Toast.makeText(MainActivity.this, R.string.title_genre,
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.button_actor:
                                Toast.makeText(MainActivity.this, R.string.title_actor,
                                        Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.button_favorite:
                                Toast.makeText(MainActivity.this, R.string.title_favorite,
                                        Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void setView() {
        MovieRepository.RemoteSource remoteSource = new MovieRepository.RemoteSource();

        mMainViewModel =
                new MainViewModel(this.getApplicationContext(), remoteSource, new MovieAdapter(),
                        new MovieAdapter(), new MovieAdapter(), new MovieAdapter());
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setViewModel(mMainViewModel);

        DrawerLayout drawerLayout = mActivityMainBinding.layoutDrawer;
        mNavigationView = mActivityMainBinding.navigationView;
        mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
