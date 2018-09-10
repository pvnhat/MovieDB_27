package com.framgia.moviedb_27.screen.youtube;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.moviedb_27.BuildConfig;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.databinding.ActivityYoutubeBinding;
import com.framgia.moviedb_27.utils.Constants;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    public static Intent getInstance(Context context, String keyYoutube) {
        Intent intent = new Intent(context, YoutubeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.BUNDLE_TRAILER_KEY, keyYoutube);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityYoutubeBinding activityYoutubeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_youtube);
        YoutubeViewModel youtubeViewModel = new YoutubeViewModel(this.getApplicationContext());
        activityYoutubeBinding.setViewModel(youtubeViewModel);
        YouTubePlayerView youTubePlayerView = activityYoutubeBinding.youtubePlayer;
        youTubePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
            YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(getIntent().getStringExtra(Constants.BUNDLE_TRAILER_KEY));
        youTubePlayer.setFullscreen(true);
        youTubePlayer.setShowFullscreenButton(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
            YouTubeInitializationResult youTubeInitializationResult) {

    }
}
