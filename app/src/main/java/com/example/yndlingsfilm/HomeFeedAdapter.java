package com.example.yndlingsfilm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.News;

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

        private boolean liked = false;
        
        public homefeedViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePic);
            moviePic = itemView.findViewById(R.id.moviePic);
            headLine = itemView.findViewById(R.id.headline);
            time = itemView.findViewById(R.id.time);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            rating = itemView.findViewById(R.id.rating);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            thumbsUp = itemView.findViewById(R.id.thumbsUp);
            likes = itemView.findViewById(R.id.likes);


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
                .inflate(R.layout.view_homefeed, parent, false);

        homefeedViewHolder viewHolder = new homefeedViewHolder(view, mListener);
        return viewHolder;
    }


    public void onBindViewHolder(homefeedViewHolder holder, int position) {

        News news = mNewsList.get(position);

        holder.profilePic.setImageResource(news.getProfilePicResource());
        holder.moviePic.setImageResource(news.getMoviePicResource());
        holder.headLine.setText(news.getHeadLine());
        holder.time.setText(news.getTime());
        holder.movieTitle.setText(news.getMovieName());
        holder.rating.setRating(news.getRating());


    }

    public int getItemCount() {
        return mNewsList.size();
    }
}
