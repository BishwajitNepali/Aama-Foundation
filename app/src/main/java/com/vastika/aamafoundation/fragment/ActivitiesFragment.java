package com.vastika.aamafoundation.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vastika.aamafoundation.Model.ActivitiesModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.Util.Constants;
import com.vastika.aamafoundation.adapter.ActivitiesRecyclerViewAdapter;
import com.vastika.aamafoundation.adapter.AdapterNewsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ActivitiesFragment extends Fragment {

    View view;
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
   // ArrayList<ActivitiesModel> activitiesList = new ArrayList<ActivitiesModel>();

    final String TAG_TITLE = "Title";
    final String TAG_DESCRIPTION = "Description";
    String newsUrl = Constants.BASE_URL + Constants.NEWS_URL;
    RequestQueue request;

    private ActivitiesRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager llm;
    RecyclerView activities_recyclerview;
    private AdapterNewsList adapterNewsList;
    ArrayList<ActivitiesModel> activitiesList = new ArrayList<ActivitiesModel>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activities, container, false);




        activitiesList = fetchNewsData();

        Log.e("Final Act Size", activitiesList.size() + "");

        Log.e("Above adapter", "Its......");


        activities_recyclerview = (RecyclerView) view.findViewById(R.id.activity_recyclerview);
        activities_recyclerview.setHasFixedSize(true);

        llm= new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        activities_recyclerview.setLayoutManager(llm);

        adapterNewsList = new AdapterNewsList(getActivity());
        activities_recyclerview.setAdapter(adapterNewsList);

        Log.e("Not here", "Not here");
       /*
        //frag...jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout

        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) view.findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                mWaveSwipeRefreshLayout.setRefreshing(false);
                // new Task().execute();
            }
        });


        mAdapter = new ActivitiesRecyclerViewAdapter(activitiesList);

        activities_recyclerview.setAdapter(mAdapter);*/


        return view;
    }

    public ArrayList<ActivitiesModel> fetchNewsData() {

        request = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, newsUrl, new Response.Listener<JSONArray>() {

            ArrayList<ActivitiesModel> activitiesList = new ArrayList<ActivitiesModel>();

            @Override
            public void onResponse(JSONArray response) {



                for (int i = 0; i < response.length(); i++) {

                    try {

                        if (response != null) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            String title = jsonObject.getString(TAG_TITLE);
                            String description = jsonObject.getString(TAG_DESCRIPTION);
                            ActivitiesModel model = new ActivitiesModel();

                            Log.e("Title", title + "");
                            model.setTitle(title);
                            model.setDescription(description);
                            activitiesList.add(model);
                            Log.e("ActSz", activitiesList.size() + "");

                           // mAdapter.notifyDataSetChanged();

                        } else
                          showDialog();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.e("Test b4 adapter ActSz", activitiesList.size() + "");

                adapterNewsList.setNewsList(activitiesList);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showDialog();

            }

        });
        request.add(jsonObjReq);

        return activitiesList;

    }

    private void showDialog() {

        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("It's pretty, isn't it?");
        pDialog.setCustomImage(R.drawable.logo);
        pDialog.setCancelable(true);
        pDialog.show();
    }


}
