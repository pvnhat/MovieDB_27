package com.framgia.moviedb_27.data.source.local.sqlite;

import android.content.Context;
import android.database.Cursor;
import com.framgia.moviedb_27.data.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController implements SqliteController {

    private static final String TABLE_NAME = "Movie";
    private static final String VOTE_COUNT = "VoteCount";
    private static final String MOVIE_ID = "MovieId";
    private static final String VOTE_AVERAGE = "VoteAverage";
    private static final String TITLE = "Title";
    private static final String POSTER_PATH = "PosterPath";
    private static final String ORIGINAL_LANGUAGE = "OriginalLanguage";
    private static final String GENRE_LIST = "GenreList";
    private static final String BACKDROP_PATH = "BackdropPath";
    private static final String OVERVIEW = "Overview";
    private static final String RELEASE_DATE = "ReleaseDate";

    private Context mContext;
    private DatabaseSqlite mDatabaseSqlite;
    private Gson mGson;

    public DatabaseController(Context context) {
        mContext = context;
        mDatabaseSqlite = new DatabaseSqlite(mContext);
        mGson = new Gson();
    }

    public void createTable() {
        mDatabaseSqlite.writeData("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME
                + " ( "
                + VOTE_COUNT
                + " INTEGER, "
                + MOVIE_ID
                + " INTEGER PRIMARY KEY, "
                + VOTE_AVERAGE
                + " REAL, "
                + TITLE
                + " TEXT, "
                + POSTER_PATH
                + " TEXT, "
                + ORIGINAL_LANGUAGE
                + " TEXT, "
                + GENRE_LIST
                + " TEXT, "
                + BACKDROP_PATH
                + " TEXT, "
                + OVERVIEW
                + " TEXT, "
                + RELEASE_DATE
                + " TEXT) ");
    }

    @Override
    public void insertMovieToDatabase(Movie movie) {
        List<Integer> listGenre = movie.getGenreIds();
        String genreString = mGson.toJson(listGenre);

        mDatabaseSqlite.writeData(
                " INSERT INTO "
                        + TABLE_NAME
                        + " VALUES ('"
                        + movie.getVoteCount()
                        + "' , '"
                        + movie.getId()
                        + "' , '"
                        + movie.getVoteAverage()
                        + "' , '"
                        + movie.getTitle()
                        + "' , '"
                        + movie.getFavoriteImage()
                        + "' , '"
                        + movie.getOriginalLanguage()
                        + "' , '"
                        + genreString
                        + "' , '"
                        + movie.getFavoriteBackdrop()
                        + "' , '"
                        + movie.getOverview()
                        + "' , '"
                        + movie.getReleaseDate()
                        + "' )");
    }

    @Override
    public List<Movie> getListMovieFromDatabase() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie;
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = mDatabaseSqlite.readData(sql);
        while (cursor.moveToNext()) {
            movie = new Movie(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    parseJson(cursor.getString(6)), cursor.getString(7), cursor.getString(8),
                    cursor.getString(9));
            movieList.add(movie);
        }
        return movieList;
    }

    @Override
    public boolean isInFavoriteList(int movieId) {
        boolean isFavorite = false;
        Cursor cursor = mDatabaseSqlite.readData(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + MOVIE_ID + " = '" + movieId + "'");
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            isFavorite = true;
        }
        return isFavorite;
    }

    @Override
    public void deleteMovieFavorite(int movieId) {
        mDatabaseSqlite.writeData(
                "DELETE FROM " + TABLE_NAME + " WHERE " + MOVIE_ID + " = '" + movieId + "'");
    }

    /**
     * Parse jSonString to GenreList
     */
    private List<Integer> parseJson(String json) {
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        List<Integer> idList = mGson.fromJson(json, type);
        return idList;
    }
}
