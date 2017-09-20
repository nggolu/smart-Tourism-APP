package com.voyage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.voyage.Recommender.Sites;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by DU on 2/24/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder>  {
    private ArrayList<Sites> sitesArrayList;
    private Context mContext;


    public PlaceAdapter(Context context,ArrayList<Sites> sitesArrayList) {
        mContext = context;
        this.sitesArrayList = sitesArrayList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,shortDes,category;
        ImageView icon ;
        RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.tvName);
            shortDes = (TextView)view.findViewById(R.id.tvShortDes);
          //  price = (TextView)view.findViewById(R.id.tvPrice);
            rating = (RatingBar)view.findViewById(R.id.ratingBar);
            category = (TextView)view.findViewById(R.id.tvCateg);

            icon = (ImageView)view.findViewById(R.id.ivHotel);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sites sites = sitesArrayList.get(position);
        holder.name.setText(sites.getName());
        holder.shortDes.setText(sites.getShort_des());
        holder.category.setText(sites.getCategory());

    //    holder.contact.setText(hotelDetail.getContact());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        holder.rating.setRating(Float.parseFloat(decimalFormat.format(sites.getRating())));
      //  holder.rating.setRating((float)sites.getRating());
        System.out.println("LINK : " + sites.getImageLink());
        try{
            Glide.with(mContext).load(sites.getImageLink()).placeholder(R.drawable.ic_place_black).into(holder.icon);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return sitesArrayList.size();
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PlaceAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PlaceAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
