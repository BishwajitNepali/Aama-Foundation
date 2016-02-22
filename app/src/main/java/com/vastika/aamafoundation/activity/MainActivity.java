package com.vastika.aamafoundation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vastika.aamafoundation.Model.ActivitiesModel;
import com.vastika.aamafoundation.Model.CampaignModel;
import com.vastika.aamafoundation.R;
import com.vastika.aamafoundation.Util.Constants;
import com.vastika.aamafoundation.adapter.ActivitiesRecyclerViewAdapter;
import com.vastika.aamafoundation.adapter.ViewPagerAdapter;
import com.vastika.aamafoundation.fragment.ActivitiesFragment;
import com.vastika.aamafoundation.fragment.CampaignFragment;
import com.vastika.aamafoundation.fragment.DonorsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    RecyclerView campaignRecyclerView;
    CampaignModel campaignObj;
    ActivitiesRecyclerViewAdapter activitiesRecyclerViewAdapter;

    RecyclerView activities_recyclerview;
    final String TAG_TITLE = "Title";
    final String TAG_DESCRIPTION = "Description";
    String newsUrl = Constants.BASE_URL + Constants.NEWS_URL;
    RequestQueue request;


    ArrayList<ActivitiesModel> activitiesList = new ArrayList<ActivitiesModel>();
    ArrayList<ActivitiesModel> sendDataList = new ArrayList<ActivitiesModel>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*campaignRecyclerView=(RecyclerView)findViewById(R.id.campaign_recyclerview);
        campaignRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        campaignRecyclerView.setLayoutManager(mLayoutManager);

        loadCampaignData();*/

       // fetchNewsData();
       // Log.e("SendData Size", activitiesList.size() + "");

       /* if(activitiesList.size()>0){
           *//* Bundle bundle= new Bundle();
            bundle.putParcelableArrayList("dest", (ArrayList<? extends Parcelable>) activitiesList);
            ActivitiesFragment activitiesFragment=new ActivitiesFragment();
            activitiesFragment.setArguments(bundle);*//*

           Log.e("b4 sending to frag","Ready for Adapter");
            activities_recyclerview=(RecyclerView)findViewById(R.id.activity_recyclerview);
            activitiesRecyclerViewAdapter = new ActivitiesRecyclerViewAdapter(activitiesList);
            activities_recyclerview.setAdapter(activitiesRecyclerViewAdapter);


        }*/

    }

    public void fetchNewsData() {


        request = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET, newsUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){

                    try {

                        if(response!=null) {
                            JSONObject jsonObject = response.getJSONObject(i);

                            String title=jsonObject.getString(TAG_TITLE);
                            String description=jsonObject.getString(TAG_DESCRIPTION);
                            ActivitiesModel model = new ActivitiesModel();

                            Log.e("Title",title+"");
                            model.setTitle(title);
                            model.setDescription(description);
                            activitiesList.add(model);
                            Log.e("ActSz",activitiesList.size()+"");

                           // activitiesRecyclerViewAdapter.notifyDataSetChanged();

                        }
                        else
                            fetchNewsData();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showDialog();

            }
        });
        request.add(jsonObjReq);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActivitiesFragment(), "ACTIVITIES");
        adapter.addFragment(new CampaignFragment(), "CAMPAIGN");
        adapter.addFragment(new DonorsFragment(), "DONORS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_introduction) {
            // Handle the camera action
            showDialog();

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_videos) {

        } else if (id == R.id.nav_contact_us) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showDialog() {

        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("It's pretty, isn't it?");
        pDialog.setCustomImage(R.drawable.logo);
        pDialog.setCancelable(true);
        pDialog.show();
    }

/*
    @Override
    protected void onResume() {
        super.onResume();
        ((CampaignRecylerViewAdapter) mAdapter).setOnItemClickListener(new CampaignRecylerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                //Toast.makeText(MainActivity.this,"Clicked on Item " + position,Toast.LENGTH_SHORT).show();

            }
        });
    }*/
}
