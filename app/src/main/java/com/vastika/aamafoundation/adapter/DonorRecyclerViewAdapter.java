package com.vastika.aamafoundation.adapter;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vastika.aamafoundation.Model.DonorModel;
import com.vastika.aamafoundation.R;

import java.util.ArrayList;

public class DonorRecyclerViewAdapter extends RecyclerView
        .Adapter<DonorRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DonorModel> mDataset;
    static ImageView image;

    static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;


        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.donorName);
            image = (ImageView) itemView.findViewById(R.id.newsLogo);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // myClickListener.onItemClick(getPosition(), v);
        }
    }


    public DonorRecyclerViewAdapter(ArrayList<DonorModel> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donor_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getTitle());
        // holder.image.setImageResource(mDataset.get(position).getImage());
        Picasso.with(context).load(R.drawable.man).centerCrop().fit().into(image);
    }

    public void addItem(DonorModel dataObj, int index) {
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

