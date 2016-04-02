package com.biotuandroidAplica.biologytu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biotuandroidAplica.biologytu.R;
import com.biotuandroidAplica.biologytu.model.NavDrawerModel;

import java.util.List;

/**
 * Created by Dell on 4/2/2016.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    private List<NavDrawerModel> drawerList;

    public NavDrawerAdapter(List<NavDrawerModel> drawerList) {
        this.drawerList = drawerList;
    }

    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_nav_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(NavDrawerAdapter.ViewHolder holder, int position) {

        NavDrawerModel model = drawerList.get(position);
        holder.icon.setImageResource(model.getIcon());
        holder.title.setText(model.getTitle());

    }

    @Override
    public int getItemCount() {
        return drawerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
