package com.api.integration.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.api.integration.R;
import com.api.integration.network.models.commits.Item;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CommitsInfoAdapter extends RecyclerView.Adapter<CommitsInfoAdapter.ViewHolder> {
    private List<Item> items;

    public CommitsInfoAdapter(List<Item> itemList) {
        super();
        this.items = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.commits_info_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvCommitterName.setText(items.get(i).commit.committer.name);
        viewHolder.tvCommitterEmail.setText(items.get(i).commit.committer.email);
        viewHolder.tvCommitterMessage.setText(items.get(i).commit.message);
        try {
            if ((!items.get(i).committer.avatarUrl.equals(null)) && (!items.get(i).committer.avatarUrl.equals(""))) {
                Picasso.with(viewHolder.itemView.getContext()).load(items.get(i).committer.avatarUrl).into(viewHolder.avatarImage);
            } else {
                Picasso.with(viewHolder.itemView.getContext()).load(R.mipmap.ic_launcher_round).into(viewHolder.avatarImage);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatarImage;
        public TextView tvCommitterName;
        public TextView tvCommitterEmail;
        public TextView tvCommitterMessage;


        public ViewHolder(View itemView) {
            super(itemView);
            avatarImage = (ImageView) itemView.findViewById(R.id.avatar_img);
            tvCommitterName = (TextView) itemView.findViewById(R.id.tv_committer_name);
            tvCommitterEmail = (TextView) itemView.findViewById(R.id.tv_committer_email);
            tvCommitterMessage = (TextView) itemView.findViewById(R.id.tv_committer_message);
        }
    }
}




