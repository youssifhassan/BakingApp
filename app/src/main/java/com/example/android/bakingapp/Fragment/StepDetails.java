package com.example.android.bakingapp.Fragment;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.Model.Step;
import com.example.android.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;


public class StepDetails extends Fragment {

    TextView textView;
    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;
    boolean playWhenReady;
    int currentWindow;
    long playbackPosition;
    Step step;
    TextView gone;
    ImageView thumb;


    public StepDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.step_details_fragment, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarId);
        toolbar.setTitle("Detailed Step");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        textView = (TextView)view.findViewById(R.id.step_full_desc);
        gone = (TextView)view.findViewById(R.id.gone);
        playerView = (SimpleExoPlayerView) view.findViewById(R.id.video_view);
        thumb = (ImageView) view.findViewById(R.id.thumb);

        currentWindow = 0;
        playbackPosition = 0;
        playWhenReady = false;

        if(savedInstanceState!=null){

            releasePlayer();
            currentWindow = savedInstanceState.getInt("window");
            playbackPosition = savedInstanceState.getLong("position");
            playWhenReady = savedInstanceState.getBoolean("play");
            step=savedInstanceState.getParcelable("sstep");
            initializePlayer(step);
        }

        if (getFragmentManager().findFragmentById(R.id.stepsFragment)==null){
            toolbar.setVisibility(View.VISIBLE);
            step= getArguments().getParcelable("step");
            textView.setText(step.getDesc());
            setImage(step.getThumbUrl());
            initializePlayer(step);

        }else{

            toolbar.setVisibility(View.INVISIBLE);
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        }

        return view;
    }


    public void reset(){
        playbackPosition=0;
        currentWindow=0;
        playWhenReady=false;
    }

    public void setDesc(String desc){
        textView.setText(desc);
    }

    public void initializePlayer(Step step) {
        if(player!=null){
            return;
        }

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);


        if (step.getVideoUrl() != null)
        {
            Uri uri = Uri.parse(step.getVideoUrl());
            if (step.getVideoUrl().equals("")){
                playerView.setVisibility(View.INVISIBLE);
                gone.setVisibility(View.VISIBLE);
            }
            else{
                playerView.setVisibility(View.VISIBLE);
                gone.setVisibility(View.INVISIBLE);
            }
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);
        }

    }

    public void setImage(String url){
       if(!TextUtils.isEmpty(url)){Picasso
               .with(getActivity())
               .load(url)
               .placeholder(R.drawable.placeholder) // can also be a drawable
               .error(R.drawable.placeholder) // will be displayed if the image cannot be loaded
               .into(thumb);}
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
           // initializePlayer(step);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

            initializePlayer(step);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(player!=null){

            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();

        }

        outState.putInt("window",currentWindow);
        outState.putLong("position",playbackPosition);
        outState.putBoolean("play",playWhenReady);
        outState.putParcelable("sstep",step);

    }

    public void setStep(Step step) {
        this.step = step;
    }

    @Override
    public void onPause() {
        super.onPause();

            releasePlayer();

    }

    @Override
    public void onStop() {
        super.onStop();

            releasePlayer();

    }

    public void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
           currentWindow = player.getCurrentWindowIndex();
           playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
