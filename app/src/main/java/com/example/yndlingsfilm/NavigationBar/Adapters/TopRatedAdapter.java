package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yndlingsfilm.Model.Friendlist;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;

public class TopRatedAdapter extends RecyclerView.Adapter<FriendlistAdapter.FriendlistViewHolder> {
    private ArrayList<Friendlist> mfriendlist;

    @NonNull
    @Override
    public FriendlistAdapter.FriendlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toprated_item, parent, false);
        FriendlistAdapter.FriendlistViewHolder friendlistVH = new FriendlistAdapter.FriendlistViewHolder(view);
        return friendlistVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendlistAdapter.FriendlistViewHolder holder, int position) {
        Friendlist currentFriend = mfriendlist.get(position);

        Glide.with(holder.itemView.getContext()).load(currentFriend.getImageResource()).into(holder.friendlistPic);

        holder.friendlistName.setText(currentFriend.getFriendListName());
        holder.friendlistReviews.setText(Integer.toString(currentFriend.getFriendListReviews()));


    }

    @Override
    public int getItemCount() {
        return mfriendlist.size();
    }

    public static class FriendlistViewHolder extends RecyclerView.ViewHolder{

        public ImageView friendlistPic;
        public TextView friendlistName;
        public TextView friendlistReviews;

        public FriendlistViewHolder(@NonNull View itemView) {
            super(itemView);

            friendlistPic = itemView.findViewById(R.id.friendListCardPic);
            friendlistName = itemView.findViewById(R.id.friendListCardName);
            friendlistReviews = itemView.findViewById(R.id.friendListCardReviews);

        }
    }

    public TopRatedAdapter(ArrayList<Friendlist> friendlist){
        mfriendlist = friendlist;
    }
}


