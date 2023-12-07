package com.vsl.marveldatas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MarvelViewHolder> {

    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private List<DataModel> list;

    public DataAdapter(Context context, OnItemClickListener onItemClickListener, List<DataModel> list) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.list = list;
    }

    @NonNull
    @Override
    public MarvelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_marvel, parent, false);
        return new DataAdapter.MarvelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        holder.team.setText(list.get(position).getTeam());
        holder.createdby.setText(list.get(position).getCreatedby());

        holder.realname.setText(list.get(position).getRealname());
        holder.firstappearance.setText(list.get(position).getFirstappearance());
        holder.publisher.setText(list.get(position).getPublisher());
        holder.bio.setText(list.get(position).getBio());

        Picasso.get().load(list.get(position).getImageurl()).into(holder.imageurl);

        holder.cardView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateDataList(List<DataModel> data) {
        this.list = data;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    static class MarvelViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imageurl;
        protected CardView cardView;
        protected TextView name, realname, team, createdby, firstappearance, publisher, bio;

        public MarvelViewHolder(@NonNull View itemView) {
            super(itemView);

            imageurl = itemView.findViewById(R.id.imageurl);
            cardView = itemView.findViewById(R.id.cardView);

            name = itemView.findViewById(R.id.name);
            realname = itemView.findViewById(R.id.realname);
            team = itemView.findViewById(R.id.team);
            createdby = itemView.findViewById(R.id.createdby);
            firstappearance = itemView.findViewById(R.id.firstappearance);
            publisher = itemView.findViewById(R.id.publisher);
            bio = itemView.findViewById(R.id.bio);

        }
    }
}
