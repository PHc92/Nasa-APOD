package com.gs.nasa.apod;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gs.nasa.apod.databinding.SearchLayoutBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private SearchLayoutBinding binding;
    private String date;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SearchLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.searchButton.setOnClickListener(view1 -> {
            date = String.valueOf(binding.EnterDateText.getText());
            Log.e("DATE", date);
            Fragment displayFragment = new DisplayFragment(date);
            getFragmentManager().beginTransaction().replace(R.id.main,displayFragment).commit();
        });

    }


}
