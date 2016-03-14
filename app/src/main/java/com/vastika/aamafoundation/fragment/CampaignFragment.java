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
import com.vastika.aamafoundation.Model.CampaignModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.Util.Constants;
import com.vastika.aamafoundation.adapter.CampaignRecylerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CampaignFragment extends Fragment {

    View view;

    final String TAG_TITLE = "Title";
    final String TAG_DESCRIPTION = "Description";
    final String  TAG_ID="ID";
    final String  TAG_IMAGE="Image";
    final String  TAG_EVENT_DATE="EventDate";
    final String  TAG_RELATED="Related";

    boolean FLAG = false;
    ProgressDialog pDialog;

    String newsUrl = Constants.BASE_URL + Constants.CAMPAIGN_URL;
    RequestQueue request;
    CampaignRecylerViewAdapter campaignRecylerViewAdapter;

    RecyclerView campaignRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<CampaignModel> campaignList = new ArrayList<CampaignModel>();

    public CampaignFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_campaign, container, false);
        if (!FLAG  ) {
            fetchCampaignData();
            populateData();
        } else
            populateData();


        return view;
    }

    private void populateData() {

        campaignRecyclerView = (RecyclerView) view.findViewById(R.id.campaign_recyclerview);
        campaignRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        campaignRecyclerView.setLayoutManager(mLayoutManager);

        campaignRecylerViewAdapter = new CampaignRecylerViewAdapter(campaignList,getContext());
        campaignRecyclerView.setAdapter(campaignRecylerViewAdapter);
    }

    public void fetchCampaignData() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        request = Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, newsUrl, new Response.Listener<JSONArray>() {

            ArrayList<CampaignModel> sendCampaignList = new ArrayList<CampaignModel>();

            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {

                    try {

                        if (response != null) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            String title = jsonObject.getString(TAG_TITLE);
                            String description = jsonObject.getString(TAG_DESCRIPTION);
                            int id =Integer.parseInt(jsonObject.getString(TAG_ID));
                            String image=jsonObject.getString(TAG_IMAGE);
                            String eventdate=jsonObject.getString(TAG_EVENT_DATE);
                            String related=jsonObject.getString(TAG_RELATED);
                            CampaignModel model = new CampaignModel();

                            Log.e("Title", title + "");
                            model.setTitle(title);
                            model.setDescription(description);
                            model.setID(id);
                            model.setImage(image);
                            model.setEventDate(eventdate);
                            model.setRelated(related);

                            sendCampaignList.add(model);
                            Log.e("ActSz", sendCampaignList.size() + "");
                            campaignRecylerViewAdapter.notifyDataSetChanged();


                        } else
                            showDialog();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                saveData(sendCampaignList);
                Log.e("Test b4 adapter ActSz", sendCampaignList.size() + "");
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

    private void saveData(ArrayList<CampaignModel> sendCampaignList) {

        campaignList.addAll(sendCampaignList);

        //adapterNewsList.setNewsList(activitiesList);
        Log.e("K aayo save vitra", campaignList.size() + "");

    }

    private void showDialog() {

        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Oops Something is wrong?");
        pDialog.setCustomImage(R.drawable.logo);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}
