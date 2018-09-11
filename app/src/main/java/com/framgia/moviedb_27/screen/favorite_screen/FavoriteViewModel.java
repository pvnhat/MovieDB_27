package com.framgia.moviedb_27.screen.favorite_screen;

import android.content.Context;
import android.widget.Toast;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_27.screen.BaseViewModel;
import com.framgia.moviedb_27.screen.detail.DetailActivity;
import com.framgia.moviedb_27.screen.list_movie_screen.ListMovieActivity;
import com.framgia.moviedb_27.screen.main.MovieAdapter;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import java.util.List;

public class FavoriteViewModel extends BaseViewModel
        implements OnClickListener.OnItemClickListener {
    private Context mContext;
    private MovieAdapter mMovieAdapter;
    private MovieRepository.LocalSource mRepositoryLocalDataSource;
    private List<Movie> mMovieList;

    FavoriteViewModel(Context context) {
        mContext = context;
        mMovieAdapter = new MovieAdapter();
        mRepositoryLocalDataSource =
                new MovieRepository.LocalSource(new MovieLocalDataSource(context));
        mMovieAdapter.setOnItemClick(this);
    }

    private void onLoadData() {
        if (mRepositoryLocalDataSource.getListMovieFromDatabase() != null) {
            mMovieList = mRepositoryLocalDataSource.getListMovieFromDatabase();
            mMovieAdapter.updateMovie(mMovieList);
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.text_null_favorite),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public MovieAdapter getFavoriteAdapter() {
        return mMovieAdapter;
    }

    @Override
    protected void onStart() {
        onLoadData();
    }

    @Override
    protected void onStop() {
    }


    @Override
    public void onItemClick(Object movie) {
        mContext.startActivity(DetailActivity.newInstance(mContext, (Movie) movie));
    }
}
