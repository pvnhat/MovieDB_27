package com.framgia.moviedb_27.screen.list_movie_screen;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.databinding.ActivityListMovieBinding;
import com.framgia.moviedb_27.utils.Constants;

public class ListMovieActivity extends AppCompatActivity {

    private static final String EXTRA_KEY = "EXTRA_KEY";
    private static final String EXTRA_VALUE = "EXTRA_VALUE";

    private ListMovieViewModel mListMovieViewModel;
    private boolean mIsScrolling;
    private int mCurrenItem, mTotalItem, mScrollOutItem;
    private ProgressBar mProgressBar;

    public static Intent newInstance(Context context, String key, String value) {
        Intent intent = new Intent(context, ListMovieActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA_KEY, key);
        intent.putExtra(EXTRA_VALUE, value);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter();
        mListMovieViewModel =
                new ListMovieViewModel(this.getApplicationContext(), listMovieAdapter);

        Intent intent = getIntent();
        String key = intent.getStringExtra(EXTRA_KEY);
        String value = intent.getStringExtra(EXTRA_VALUE);
        mListMovieViewModel.setQueryData(key, value);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(value);

        ActivityListMovieBinding movieBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_list_movie);
        movieBinding.setVewModel(mListMovieViewModel);
        loadMoreItem(movieBinding);
    }

    private void loadMoreItem(ActivityListMovieBinding movieBinding) {
        RecyclerView recyclerView = movieBinding.recyclerListMovie;
        mProgressBar = movieBinding.progressLoadmore;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int page = Constants.NUM_ONE;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mCurrenItem = recyclerView.getChildCount();
                mTotalItem = recyclerView.getLayoutManager().getItemCount();
                mScrollOutItem =
                        ((LinearLayoutManager) recyclerView.getLayoutManager())
                                .findFirstVisibleItemPosition();
                if (!mIsScrolling && (mCurrenItem + mScrollOutItem == mTotalItem)) {
                    page++;
                    mIsScrolling = false;
                    mProgressBar.setVisibility(View.VISIBLE);
                    mListMovieViewModel.fetchMoreData(page);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListMovieViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mListMovieViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
