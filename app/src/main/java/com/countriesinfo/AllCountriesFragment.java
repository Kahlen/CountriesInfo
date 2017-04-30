package com.countriesinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.countriesinfo.models.AllCountriesFetcher;
import com.futuremind.recyclerviewfastscroll.FastScroller;

public class AllCountriesFragment extends Fragment {

    private OnReceiveCountryDetailInfoListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.all_countries_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.countries_list);
        final CountriesRecyclerAdapter adapter =
                new CountriesRecyclerAdapter(
                    getContext(),
                    AllCountriesFetcher.fetchAllCountries(getResources()),
                    mListener);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final FastScroller fastScroller = (FastScroller) view.findViewById(R.id.fast_scroll);
        fastScroller.setRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnReceiveCountryDetailInfoListener){
            mListener = (OnReceiveCountryDetailInfoListener) context;
        }
    }
}
