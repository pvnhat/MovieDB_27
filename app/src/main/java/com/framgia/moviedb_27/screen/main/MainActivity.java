package com.framgia.moviedb_27.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_27.databinding.ActivityMainBinding;
import com.framgia.moviedb_27.screen.actor.ActorActivity;
import com.framgia.moviedb_27.screen.favorite_screen.FavoriteActivity;
import com.framgia.moviedb_27.screen.genre.GenreActivity;
import com.framgia.moviedb_27.screen.list_movie_screen.ListMovieActivity;

public class MainActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, SearchView.OnQueryTextListener {

    private static final int NUMBER_OF_BANNER = 5;
    private static final int MARGIN_LEFT_RIGHT_DOTS = 4;
    private static final String SEARCH_KEY = "SEARCH_KEY";

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private LinearLayout mLinearDots;
    private SearchView mSearchView;

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
                                startActivity(GenreActivity.getInstance(MainActivity.this));
                                return true;
                            case R.id.button_actor:
                                startActivity(ActorActivity.getInstance(MainActivity.this));
                                return true;
                            case R.id.button_favorite:
                                startActivity(FavoriteActivity.newInstance(MainActivity.this));
                                return true;
                        }
                        return false;
                    }
                });
    }

    private void setView() {
        MovieRepository.RemoteSource remoteSource =
                new MovieRepository.RemoteSource(new MovieRemoteDataSource());

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this.getApplicationContext());
        MainViewModel mainViewModel =
                new MainViewModel(this.getApplicationContext(), remoteSource, new MovieAdapter(),
                        new MovieAdapter(), new MovieAdapter(), new MovieAdapter(),
                        mainPagerAdapter);
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(mainViewModel);

        DrawerLayout drawerLayout = activityMainBinding.layoutDrawer;
        mNavigationView = activityMainBinding.navigationView;
        mActionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mLinearDots = activityMainBinding.linearDots;
        activityMainBinding.viewpagerBanner.setOnPageChangeListener(this);
        onCreateDots(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onCreateDots(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void onCreateDots(int currentPosition) {
        if (mLinearDots != null) {
            mLinearDots.removeAllViews();
        }
        ImageView[] imageDots = new ImageView[NUMBER_OF_BANNER];

        for (int i = 0; i < NUMBER_OF_BANNER; i++) {
            imageDots[i] = new ImageView(this);
            if (i == currentPosition) {
                imageDots[i].setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.active_dots));
            } else {
                imageDots[i].setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.unactive_dots));
            }
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(MARGIN_LEFT_RIGHT_DOTS, 0, MARGIN_LEFT_RIGHT_DOTS, 0);
            mLinearDots.addView(imageDots[i], layoutParams);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view_menu);
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String keyWord) {
        mSearchView.setQuery("", false);
        mSearchView.setIconified(true);
        startActivity(ListMovieActivity.newInstance(this, SEARCH_KEY, keyWord));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
