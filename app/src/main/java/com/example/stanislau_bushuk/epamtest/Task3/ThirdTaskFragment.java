package com.example.stanislau_bushuk.epamtest.Task3;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.stanislau_bushuk.epamtest.API.Request;
import com.example.stanislau_bushuk.epamtest.Adapter.ListViewAdapterTask3;
import com.example.stanislau_bushuk.epamtest.Modele.ListPhotoRealm;
import com.example.stanislau_bushuk.epamtest.Modele.PhotoRealm;
import com.example.stanislau_bushuk.epamtest.R;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdTaskFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListViewAdapterTask3 adapter;
    private View view;
    private ImageView errorImage;
    private Realm realm;
    private ListPhotoRealm listPhotosRealm;
    private ListPhotoRealm tempListPhotoRealm;
    private RealmList<PhotoRealm> tempList;
    private RealmList<PhotoRealm> nowList;
    private boolean flag = true;


    public ThirdTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            Timber.e(String.valueOf(savedInstanceState.getInt("fragment")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second_task, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.Part3));
        recyclerView = view.findViewById(R.id.list);
        errorImage = view.findViewById(R.id.ErrorImage);
        realm = Realm.getDefaultInstance();
        listPhotosRealm = realm.where(ListPhotoRealm.class).findFirst();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        if (listPhotosRealm != null) {
            nowList = listPhotosRealm.getPhotos();
        } else {
            realm.beginTransaction();
            listPhotosRealm = realm.createObject(ListPhotoRealm.class);
            realm.commitTransaction();
        }
        adapter = new ListViewAdapterTask3(getActivity(), listPhotosRealm);
        recyclerView.setAdapter(adapter);
        getResponse();
    }


    public void getResponse() {
        Request.getIapi().getJson().enqueue(new Callback<ListPhotoRealm>() {
            @Override
            public void onResponse(@NonNull Call<ListPhotoRealm> call, @NonNull Response<ListPhotoRealm> response) {
                realm.beginTransaction();
                tempListPhotoRealm = realm.createObject(ListPhotoRealm.class);
                for (PhotoRealm photo : response.body().getPhotos()) {
                    tempListPhotoRealm.getPhotos().add(realm.copyToRealm(new PhotoRealm(photo.getTitle(), photo.getDescription(),
                            photo.getUrl(), photo.getId(), photo.getLatitude(), photo.getLongitude())));
                }
                tempList = tempListPhotoRealm.getPhotos();

                if (nowList != null) {
                    if(nowList.size()==tempList.size()){
                        for(int i=0;i<nowList.size();i++){
                            if(nowList.get(i).getId()!=nowList.get(i).getId()){
                                listPhotosRealm.setPhotos(tempListPhotoRealm.getPhotos());
                                adapter.notifyDataSetChanged();
                                Timber.e("notify id");
                                break;
                            }
                        }
                    }
                    else {
                        listPhotosRealm.setPhotos(tempListPhotoRealm.getPhotos());
                        adapter.notifyDataSetChanged();
                        Timber.e("notify size");
                    }
                }else {
                    listPhotosRealm.setPhotos(tempListPhotoRealm.getPhotos());
                    adapter.notifyDataSetChanged();
                    Timber.e("notify null");
                }
                realm.commitTransaction();
            }

            @Override
            public void onFailure(@NonNull Call<ListPhotoRealm> call, @NonNull Throwable t) {
                t.printStackTrace();
                ListPhotoRealm realmResults = realm.where(ListPhotoRealm.class).findFirst();
                if (realmResults != null) {
                    if (!realmResults.getPhotos().isEmpty()) {
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        adapter = new ListViewAdapterTask3(getActivity(), realmResults);
                        recyclerView.setAdapter(adapter);
                    } else {
                        errorImage.setImageResource(R.drawable.eror);
                    }
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
