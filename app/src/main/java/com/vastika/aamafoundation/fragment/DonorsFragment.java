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
import com.vastika.aamafoundation.Model.DonorModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.Util.Constants;
import com.vastika.aamafoundation.adapter.DonorRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DonorsFragment extends Fragment {

    View view;

    final String TAG_TITLE = "Title";
    final String TAG_DESCRIPTION = "Description";
    String newsUrl = Constants.BASE_URL + Constants.NEWS_URL;
    RequestQueue request;
    boolean FLAG=false;
    ProgressDialog pDialog;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView donors_recyclerview;
    ArrayList<DonorModel> donorsList = new ArrayList<DonorModel>();
    DonorRecyclerViewAdapter donorsRecyclerViewAdapter;

    public DonorsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_donors, container, false);

        if(!FLAG) {
            fetchDonorsData();
            populateData();
        }
        else
            populateData();


        return view;
    }

    private void populateData() {

        donors_recyclerview = (RecyclerView) view.findViewById(R.id.donors_recyclerview);
        donors_recyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        donors_recyclerview.setLayoutManager(mLayoutManager);

        donorsRecyclerViewAdapter = new DonorRecyclerViewAdapter(donorsList);
        donors_recyclerview.setAdapter(donorsRecyclerViewAdapter);
    }

    public void fetchDonorsData() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        request = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, newsUrl, new Response.Listener<JSONArray>() {

            ArrayList<DonorModel> sendActivitiesList = new ArrayList<DonorModel>();

            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {

                    try {

                        if (response != null) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            String title = jsonObject.getString(TAG_TITLE);
                            String description = jsonObject.getString(TAG_DESCRIPTION);
                            DonorModel model = new DonorModel();

                            Log.e("Title", title + "");
                            model.setTitle(title);
                            model.setDescription(description);
                            sendActivitiesList.add(model);
                            Log.e("ActSz", sendActivitiesList.size() + "");
                            donorsRecyclerViewAdapter.notifyDataSetChanged();


                        } else
                            showDialog();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                saveData(sendActivitiesList);
                Log.e("Test b4 adapter ActSz", donorsList.size() + "");
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

    private void saveData(ArrayList<DonorModel> sendDonorsList) {

        donorsList.addAll(sendDonorsList);
        FLAG=true;

        //adapterNewsList.setNewsList(activitiesList);
        Log.e("K aayo save vitra", donorsList.size() + "");


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
