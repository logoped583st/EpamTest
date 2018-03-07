package com.example.stanislau_bushuk.epamtest;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask2;
import com.example.stanislau_bushuk.epamtest.Module.Element;
import com.example.stanislau_bushuk.epamtest.Module.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondTaskFragment extends Fragment {


    private ArrayList<Photo> arrayPhoto;
    private ArrayList<ArrayList<Photo>> arrayLists;
    private GridView gridView;


    public SecondTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_second_task, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = view.findViewById(R.id.list);
        arrayPhoto = new ArrayList<>();
        arrayLists = new ArrayList<>();

        Load load = new Load();
        load.execute();
    }


    //////////
    public String readFromFile() {
        String json;
        try {
            InputStream is = (App.context).getAssets().open("upt7z.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getResponse(String body) {

        try {
            JSONObject photos = new JSONObject(body);
            JSONArray jsonArray = photos.getJSONArray("photos");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                arrayPhoto.add(new Photo(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("url"), jsonObject.getDouble("longitude"),
                        jsonObject.getDouble("latitude")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class Load extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            getResponse(readFromFile());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ListViewAdapterTask2 adapter = new ListViewAdapterTask2(getActivity().getBaseContext(), arrayPhoto);
            gridView.setAdapter(adapter);
        }
    }
}
