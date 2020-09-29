package com.example.yndlingsfilm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.homefeedViewHolder> {
   private ArrayList<SingleNews> mSingleNewsList;
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
        public TextView text;
        public TextView movieTitle;
        public TextView rating;
        
        public homefeedViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePic);
            moviePic = itemView.findViewById(R.id.moviePic);
            headLine = itemView.findViewById(R.id.headline);
            text = itemView.findViewById(R.id.text);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            rating = itemView.findViewById(R.id.rating);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public HomeFeedAdapter(ArrayList<SingleNews> singleNewsList) {
        mSingleNewsList = singleNewsList;
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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        SingleNews singleNews = mSingleNewsList.get(position);

        holder.profilePic.setImageResource(singleNews.getProfilePicResource());
        holder.moviePic.setImageResource(singleNews.getMoviePicResource());
        holder.headLine.setText(singleNews.getHeadLine());
        holder.text.setText(singleNews.getText());
        holder.movieTitle.setText(singleNews.getMovieName());
        holder.rating.setText(singleNews.getRating());


    }

    public int getItemCount() {
        return mSingleNewsList.size();
    }
}
