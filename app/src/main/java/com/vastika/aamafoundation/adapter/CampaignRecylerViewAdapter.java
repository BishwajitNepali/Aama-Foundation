package com.vastika.aamafoundation.adapter;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vastika.aamafoundation.Model.CampaignModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.activity.NewsDetailActivity;

import java.util.ArrayList;

public class CampaignRecylerViewAdapter extends RecyclerView
        .Adapter<CampaignRecylerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static ArrayList<CampaignModel> mDataset;
    static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        ImageView image;
        Button donate,read,share;

        public DataObjectHolder(View itemView) {
            super(itemView);
             label = (TextView) itemView.findViewById(R.id.campaign_title);
            //image = (ImageView) itemView.findViewById(R.id.image);
            donate=(Button)itemView.findViewById(R.id.donate);
            read=(Button)itemView.findViewById(R.id.read);
            share=(Button)itemView.findViewById(R.id.share);

            donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Snackbar.make(v,"Dont worry paypal coming soon!",Snackbar.LENGTH_LONG).show();

                }
            });

            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i= new Intent(context, NewsDetailActivity.class);

                    i.putExtra("title",mDataset.get(getAdapterPosition()).getTitle()+"");
                    i.putExtra("description", mDataset.get(getAdapterPosition()).getDescription() + "");

                    context.startActivity(i);

                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://aamafoundation.org");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Learn to Love ");
                    context.startActivity(Intent.createChooser(sharingIntent, "Heartly Share"));


                }
            });

            Log.i(LOG_TAG, "Adding Listener");
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // myClickListener.onItemClick(getPosition(), v);


        }
    }



    public CampaignRecylerViewAdapter(ArrayList<CampaignModel> myDataset, Context context) {
        mDataset = myDataset;
        this.context=context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campaign_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
         holder.label.setText(mDataset.get(position).getTitle());
        // holder.image.setImageResource(mDataset.get(position).getImage());
    }

    public void addItem(CampaignModel dataObj, int index) {
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

