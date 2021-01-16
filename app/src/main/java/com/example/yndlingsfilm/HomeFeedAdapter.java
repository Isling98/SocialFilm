package com.example.yndlingsfilm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.News;
import com.example.yndlingsfilm.NavigationBar.Adapters.SearchViewHolder;
import com.example.yndlingsfilm.util.Constants;

import java.util.ArrayList;


public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.homefeedViewHolder> {
   private ArrayList<News> mNewsList;
   private OnItemClickListener mListener;

   public interface OnItemClickListener{
       void onItemClick(int position);
   }

   public void setOnItemClickListener(OnItemClickListener listener){
       mListener = listener;
   }
    
    public static class homefeedViewHolder extends RecyclerView.ViewHolder {
        public ImageView profilePic;
        public ImageView moviePic;
        public TextView headLine;
        public TextView time;
        public TextView movieTitle;
        public RatingBar rating;
        public View like;
        public View comment;
        public ImageView thumbsUp;
        public TextView likes;
        public TextView reviewtext;

        private boolean liked = false;
        
        public homefeedViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePic);
            moviePic = itemView.findViewById(R.id.moviePic);
            headLine = itemView.findViewById(R.id.headline);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            rating = itemView.findViewById(R.id.rating);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            thumbsUp = itemView.findViewById(R.id.thumbsUp);

            reviewtext = itemView.findViewById(R.id.textView3);


            like.setOnClickListener(view -> {
                if(!liked){
                    liked = true;
                    thumbsUp.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                } else {
                    liked = false;
                    thumbsUp.setImageResource(R.drawable.ic_baseline_thumb_up_24_light);
                }
            });

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //åben dialog?
                }
            });

            itemView.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }


    public HomeFeedAdapter(ArrayList<News> newsList) {
        mNewsList = newsList;
    }


    public HomeFeedAdapter.homefeedViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_homepage, parent, false);

        homefeedViewHolder viewHolder = new homefeedViewHolder(view, mListener);
        return viewHolder;
    }


    public void onBindViewHolder(homefeedViewHolder holder, int position) {

        News news = mNewsList.get(position);


        Glide.with(holder.itemView.getContext()).load(news.getMovieUrl()).into(holder.moviePic);
        Glide.with(holder.itemView.getContext()).load(news.getProfileUrl()).into(holder.profilePic);

        holder.headLine.setText(news.getHeadLine());
        holder.movieTitle.setText(news.getMovieName());
        holder.rating.setRating(news.getRating());
        holder.reviewtext.setText(news.getReviewInText());


    }

    public int getItemCount() {
        return mNewsList.size();
    }
}
