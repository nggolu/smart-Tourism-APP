package com.voyage;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.voyage.Recommender.Sites;

public class SiteDetail extends AppCompatActivity  {
    Sites site;
    ImageView siteImage;
    TextView contact, openingHrs ,address,shortDes, longDes , name;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        site = (Sites) getIntent().getSerializableExtra("currentSite");
        setContentView(R.layout.activity_site_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        initCollapsingToolbar();

        name = (TextView) findViewById(R.id.tvName);
        contact =(TextView) findViewById(R.id.tvContact);
        openingHrs =(TextView) findViewById(R.id.tvOpenHrs);
        address = (TextView) findViewById(R.id.tvAddress);
        shortDes =(TextView) findViewById(R.id.tvShortDes);
        longDes = (TextView) findViewById(R.id.tvLongDes);
        rating = (RatingBar) findViewById(R.id.rating);

        name.setText(site.getName().toString());
        contact.setText(site.getPhone().toString());
        openingHrs.setText(site.getHours().toString());
        address.setText(site.getAddress().toString());
        shortDes.setText(site.getShort_des().toString());
        longDes.setText(site.getLong_des().toString());
        rating.setRating(site.getRating().floatValue());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(SiteDetail.this,MapActivity.class);
                intent.putExtra("currentSite",site);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}

