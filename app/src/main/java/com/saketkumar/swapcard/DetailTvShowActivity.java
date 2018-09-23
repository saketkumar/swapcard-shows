package com.saketkumar.swapcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.saketkumar.swapcard.models.Result;

import java.util.List;

public class DetailTvShowActivity extends AppCompatActivity {

    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w300";
    private static final String BASE_URL_IMG_BACKGROUND = "https://image.tmdb.org/t/p/w780";


    TextView title;
    TextView release;
    TextView overview;
    TextView ratings;
    ImageView detailBackdrop;
    ImageView background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtvshow);

        title = (TextView) findViewById(R.id.detail_title);
        release = (TextView) findViewById(R.id.release_date);
        overview = (TextView) findViewById(R.id.detail_overview);
        ratings = (TextView) findViewById(R.id.ratings);

        detailBackdrop = (ImageView) findViewById(R.id.detail_backdrop);

        background = (ImageView) findViewById(R.id.bgCoverImage);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        int position = bundle.getInt("POSITION");
        List<Result> list = (List<Result>) bundle.getSerializable(Intent.EXTRA_TEXT);

        String stitle = list.get(position).getTitle();
        String srelease = list.get(position).getReleaseDate();
        String soverView = list.get(position).getOverview();

        title.setText(stitle);
        release.setText(srelease);
        overview.setText(soverView);

        String sbackdrop = list.get(position).getPosterPath();

        String sbackground = list.get(position).getBackdropPath();

        loadImage(BASE_URL_IMG + sbackdrop)
                .into(detailBackdrop);

        loadImage(BASE_URL_IMG_BACKGROUND + sbackground)
                .into(background);

        Double dratings = list.get(position).getVoteAverage();

        String sratings = dratings.toString();

        ratings.setText(sratings + "/10");


    }

    private DrawableRequestBuilder<String> loadImage(@NonNull String posterPath) {
        return Glide
                .with(this)
                .load(posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                .centerCrop()
                .crossFade();
    }


}
