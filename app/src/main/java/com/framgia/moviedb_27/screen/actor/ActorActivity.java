package com.framgia.moviedb_27.screen.actor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.databinding.ActivityActorBinding;

public class ActorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityActorBinding activityActorBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_actor);
        ActorViewModel actorViewModel =
                new ActorViewModel(this.getApplicationContext(), new MovieRepository.RemoteSource(),
                        new ActorAdapter());
        activityActorBinding.setViewModel(actorViewModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
