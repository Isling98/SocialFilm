package com.example.yndlingsfilm.NavigationBar.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yndlingsfilm.Model.Friendlist;
import com.example.yndlingsfilm.R;

import java.util.ArrayList;

public class FriendlistAdapter extends RecyclerView.Adapter<FriendlistAdapter.FriendlistViewHolder> {

    private ArrayList<Friendlist> mfriendlist;

    @NonNull
    @Override
    public FriendlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_item, parent, false);
        FriendlistViewHolder friendlistVH = new FriendlistViewHolder(view);
        return friendlistVH;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendlistViewHolder holder, int position) {
        Friendlist currentFriend = mfriendlist.get(position);

        holder.friendlistPic.setImageResource(currentFriend.getImageResource());
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

    public FriendlistAdapter(ArrayList<Friendlist> friendlist){
        mfriendlist = friendlist;
    }
}
