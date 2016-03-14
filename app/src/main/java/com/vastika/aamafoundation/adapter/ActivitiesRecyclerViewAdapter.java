package com.vastika.aamafoundation.adapter;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vastika.aamafoundation.Model.ActivitiesModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.activity.NewsDetailActivity;

import java.util.ArrayList;

public class ActivitiesRecyclerViewAdapter extends RecyclerView
        .Adapter<ActivitiesRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    public static ArrayList<ActivitiesModel> mDataset;
    static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title;
        TextView description;
       // ImageView image;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.newsTitle);
            description = (TextView) itemView.findViewById(R.id.newsDesc);
            //image = (ImageView) itemView.findViewById(R.id.image);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent i= new Intent(context, NewsDetailActivity.class);
            i.putExtra("title",mDataset.get(getAdapterPosition()).getTitle()+"");
            i.putExtra("description",mDataset.get(getAdapterPosition()).getDescription()+"");

            context.startActivity(i);
        }
    }

    public ActivitiesRecyclerViewAdapter(ArrayList<ActivitiesModel> myDataset, Context context) {
        mDataset = myDataset;
        this.context=context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activities_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
         holder.title.setText(mDataset.get(position).getTitle());
         holder.description.setText(mDataset.get(position).getDescription());
        // holder.image.setImageResource(mDataset.get(position).getImage());

    }

    public void addItem(ActivitiesModel dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

