package com.vastika.aamafoundation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vastika.aamafoundation.Model.ActivitiesModel;
import com.vastika.aamafoundation.R;

import java.util.ArrayList;

/**
 * Created by Almighty Amir on 17-Feb-16.
 */
public class AdapterNewsList extends RecyclerView.Adapter <AdapterNewsList.ViewHolderNews>{

    private LayoutInflater layoutInflator;
    private ArrayList<ActivitiesModel> newsList;


    public AdapterNewsList(Context context) {
        layoutInflator=LayoutInflater.from(context);

    }


    public void setNewsList(ArrayList<ActivitiesModel> newsList){
        this.newsList=newsList;
        notifyItemRangeChanged(0,newsList.size());
    }


    @Override
    public ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("In here", "Its......");

        View view =layoutInflator.inflate(R.layout.activities_row,parent,false);

        ViewHolderNews viewHolder=new ViewHolderNews(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderNews holder, int position) {

        ActivitiesModel currentnews=newsList.get(position);
        holder.title.setText(currentnews.getTitle());
        holder.description.setText(currentnews.getDescription());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static  class ViewHolderNews extends  RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ViewHolderNews(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.newsTitle);
            description = (TextView) itemView.findViewById(R.id.newsDesc);
        }
    }


}
