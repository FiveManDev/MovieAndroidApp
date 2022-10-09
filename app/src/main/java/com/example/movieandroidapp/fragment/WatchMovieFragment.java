package com.example.movieandroidapp.fragment;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.TrackSelectionDialog;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

public class WatchMovieFragment extends Fragment {
    View mView;
    PlayerView playerView;
    ImageView fullScreen;
    boolean isFullScreen = false;
    SimpleExoPlayer player;
    ProgressBar progressBar;
    private boolean isShowingTrackSelectionDialog;
    private DefaultTrackSelector trackSelector;

    String[] speed = {"0.25x","0.5x","Normal","1.5x","2x"};
    String live_url = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_watch_movie, container, false);
        init();
        return mView;
    }

    private void init(){
        playVideo();
    }
    private void playVideo(){
        trackSelector = new DefaultTrackSelector(mView.getContext());
        player = new SimpleExoPlayer.Builder(mView.getContext()).setTrackSelector(trackSelector).build();
        playerView = mView.findViewById(R.id.playerView);
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(live_url);
        player.addMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);


        TextView speedTxt = playerView.findViewById(R.id.speed);
        playerView = mView.findViewById(R.id.playerView);
        fullScreen = playerView.findViewById(R.id.exo_fullscreen_button);
        ImageView speedBtn = playerView.findViewById(R.id.exo_playback_speed);
        progressBar = mView.findViewById(R.id.progressBar);

        speedBtn.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
            builder.setTitle("Set Speed");
            builder.setItems(speed, (dialog, which) -> {
                // the user clicked on colors[which]

                if (which==0){

                    speedTxt.setVisibility(View.VISIBLE);
                    speedTxt.setText("0.25X");
                    PlaybackParameters param = new PlaybackParameters(0.5f);
                    player.setPlaybackParameters(param);


                }  if (which==1){

                    speedTxt.setVisibility(View.VISIBLE);
                    speedTxt.setText("0.5X");
                    PlaybackParameters param = new PlaybackParameters(0.5f);
                    player.setPlaybackParameters(param);


                }
                if (which==2){

                    speedTxt.setVisibility(View.GONE);
                    PlaybackParameters param = new PlaybackParameters(1f);
                    player.setPlaybackParameters(param);


                }
                if (which==3){
                    speedTxt.setVisibility(View.VISIBLE);
                    speedTxt.setText("1.5X");
                    PlaybackParameters param = new PlaybackParameters(1.5f);
                    player.setPlaybackParameters(param);

                }
                if (which==4){


                    speedTxt.setVisibility(View.VISIBLE);
                    speedTxt.setText("2X");

                    PlaybackParameters param = new PlaybackParameters(2f);
                    player.setPlaybackParameters(param);
                }
            });
            builder.show();

        });

        fullScreen.setOnClickListener(v -> {
            if (isFullScreen) {

                getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                if (((HomeActivity)getActivity()).getSupportActionBar() != null) {
                    ((HomeActivity)getActivity()).getSupportActionBar().show();
                }

                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
                params.width = params.MATCH_PARENT;
                params.height = (int) (200 * ((HomeActivity)getActivity()).getApplicationContext().getResources().getDisplayMetrics().density);
                playerView.setLayoutParams(params);
                isFullScreen = false;
            } else {
                getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                if (((HomeActivity)getActivity()).getSupportActionBar() != null) {
                    ((HomeActivity)getActivity()).getSupportActionBar().hide();
                }

                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
                params.width = params.MATCH_PARENT;
                params.height = params.MATCH_PARENT;
                playerView.setLayoutParams(params);
                isFullScreen = true;
            }
        });
        playerView = mView.findViewById(R.id.playerView);
        ImageView setting = playerView.findViewById(R.id.exo_track_selection_view);
        setting.setOnClickListener(v -> {
            if (!isShowingTrackSelectionDialog
                    && TrackSelectionDialog.willHaveContent(trackSelector)) {
                isShowingTrackSelectionDialog = true;
                TrackSelectionDialog trackSelectionDialog =
                        TrackSelectionDialog.createForTrackSelector(
                                trackSelector,
                                /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                trackSelectionDialog.show(((HomeActivity)getActivity()).getSupportFragmentManager(), /* tag= */ null);
            }
        });

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                    player.setPlayWhenReady(true);
                } else if (state == Player.STATE_BUFFERING) {
                    progressBar.setVisibility(View.VISIBLE);
                    playerView.setKeepScreenOn(true);
                } else {
                    progressBar.setVisibility(View.GONE);
                    player.setPlayWhenReady(true);
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        player.seekToDefaultPosition();
        player.setPlayWhenReady(true);
    }

    @Override
    public void onPause() {
        player.setPlayWhenReady(false);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        player.release();
        super.onDestroy();
    }
}
