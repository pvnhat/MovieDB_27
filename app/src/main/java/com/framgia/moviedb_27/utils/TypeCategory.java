package com.framgia.moviedb_27.utils;

import android.support.annotation.StringDef;

@StringDef({
        TypeCategory.POPULAR, TypeCategory.TOP_RATED, TypeCategory.NOW_PLAYING,
        TypeCategory.UP_COMING, TypeCategory.GENRE, TypeCategory.POPULAR_ACTOR
})
public @interface TypeCategory {
    String POPULAR = "POPULAR";
    String TOP_RATED = "TOP_RATED";
    String NOW_PLAYING = "NOW_PLAYING";
    String UP_COMING = "UP_COMING";
    String GENRE = "GENRE";
    String POPULAR_ACTOR = "POPULAR_ACTOR";
    String CAST = "CAST";
    String CREW = "CREW";
}
