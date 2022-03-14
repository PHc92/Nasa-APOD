package com.gs.nasa.apod;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gs.nasa.apod.databinding.ActivityMainBinding;
import com.gs.nasa.apod.databinding.SearchLayoutBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    private ActivityMainBinding binding;
    private String APODAPi = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&&date=";
    private NetworkCall networkCall = new NetworkCall();
    private String dateRequested = null;

    DisplayFragment(String date) {
        this.dateRequested = date;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SearchFragment();
                getFragmentManager().beginTransaction().replace(R.id.main,fragment).commit();
            }
        });
        getCard();
    }

    public void updateView(String imageUrl, String imageTitle, String imageExplaination, String imageDate){
        binding.APDOImage.setImageBitmap((Bitmap) networkCall.makeServiceCall(imageUrl, "Image"));
        binding.ImageExplaination.setText(imageExplaination);
        binding.ImageExplaination.setMovementMethod(new ScrollingMovementMethod());
        binding.ImageExplaination.scrollTo(0,0);
        binding.ImageTitle.setText(imageTitle);
        binding.ImageDate.setText(imageDate);
    }

    public void getCard() {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = df.format(c);
        String url;
        if (dateRequested != null)
           url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&&date="+dateRequested;
        else
            url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&&date="+date;
        Log.e("URL", url);

        JSONObject jsonObject = (JSONObject) networkCall.makeServiceCall(url, "Json");
        Log.e("Json-Object", "" + jsonObject);
        try {
            updateView(jsonObject.get("hdurl").toString(), jsonObject.get("title").toString(),
                    jsonObject.get("explanation").toString(), jsonObject.get("date").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
