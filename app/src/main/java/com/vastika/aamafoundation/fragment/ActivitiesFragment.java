package com.vastika.aamafoundation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vastika.aamafoundation.R;

public class ActivitiesFragment extends Fragment {

    View view;

    public ActivitiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activities, container, false);

        /*historyList = (ListView) view.findViewById(R.id.history_list);

        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        medsList = dbHandler.getAllMedication();

        Log.e("getAllMedication", "" + medsList);

        HistoryAdapter adapter = new HistoryAdapter(getActivity(), medsList);
        historyList.setAdapter(adapter);*/

        return view;
    }
}
