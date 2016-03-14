package com.vastika.aamafoundation.fragment;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ActivitiesFragment extends Fragment {

    View view;
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    final String TAG_TITLE = "Title";
    final String TAG_DESCRIPTION = "Description";
    String newsUrl = Constants.BASE_URL + Constants.NEWS_URL;
    RequestQueue request;
    boolean FLAG = false;

    ProgressDialog pDialog;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView activities_recyclerview;
    ArrayList<ActivitiesModel> activitiesList = new ArrayList<ActivitiesModel>();
    ActivitiesRecyclerViewAdapter activitiesRecyclerViewAdapter;
    ActivitiesRecyclerViewAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activities, container, false);


        if (!FLAG) {
            fetchNewsData(newsUrl);
            populateData();
        } else
            populateData();

        return view;
    }

    private void populateData() {

        activities_recyclerview = (RecyclerView) view.findViewById(R.id.activity_recyclerview);
        activities_recyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        activities_recyclerview.setLayoutManager(mLayoutManager);

        activitiesRecyclerViewAdapter = new ActivitiesRecyclerViewAdapter(activitiesList, getContext());
        activities_recyclerview.setAdapter(activitiesRecyclerViewAdapter);
    }

    public void fetchNewsData(String Url) {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        request = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, Url, new Response.Listener<JSONArray>() {

            ArrayList<ActivitiesModel> sendActivitiesList = new ArrayList<ActivitiesModel>();

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
                            sendActivitiesList.add(model);
                            Log.e("ActSz", sendActivitiesList.size() + "");
                            activitiesRecyclerViewAdapter.notifyDataSetChanged();


                        } else
                            showDialog();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                saveData(sendActivitiesList);
                Log.e("Test b4 adapter ActSz", sendActivitiesList.size() + "");
                pDialog.hide();


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();

            }

        });
        request.add(jsonObjReq);


    }

    private void saveData(ArrayList<ActivitiesModel> sendActivitiesList) {

        activitiesList.addAll(sendActivitiesList);
        FLAG = true;

        //adapterNewsList.setNewsList(activitiesList);
        Log.e("K aayo save vitra", activitiesList.size() + "");


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