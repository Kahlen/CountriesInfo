package com.countriesinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.countriesinfo.models.CountryData;

public class CountryDetailFragment extends Fragment {
    private final static String PARCEL_KEY_COUNTRY_VALUE = "country_value";

    private CountryData mCountryData;
    private View mView;

    public static CountryDetailFragment newInstance(final CountryData countryData) {
        final CountryDetailFragment fragment = new CountryDetailFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(PARCEL_KEY_COUNTRY_VALUE, countryData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.country_detail_fragment, container, false);
        mCountryData = getArguments().getParcelable(PARCEL_KEY_COUNTRY_VALUE);
        setupViews();
        getActivity().setTitle(mCountryData.getName());
        return mView;
    }

    private void setupViews() {
        ((ImageView)mView.findViewById(R.id.flag_image_view)).setImageResource(mCountryData.getImageResId());

        setTextView(R.id.detail_name, R.string.detail_name, mCountryData.getName());
        setTextView(R.id.detail_native_name, R.string.detail_native_name, mCountryData.getNativeName());
        setTextView(R.id.detail_capital, R.string.detail_capital, mCountryData.getCapital());
        setTextView(R.id.detail_region, R.string.detail_region, mCountryData.getRegion());
        setTextView(R.id.detail_population, R.string.detail_population, mCountryData.getPopulation());
        setTextView(R.id.detail_demonym, R.string.detail_demonym, mCountryData.getDemonym());
        setTextView(R.id.detail_area, R.string.detail_area, mCountryData.getArea());
        setTextView(R.id.detail_language, R.string.detail_language, mCountryData.getLanguage());
        setTextView(R.id.detail_currency, R.string.detail_currency, mCountryData.getCurrencies());
    }

    private void setTextView(final int textViewResId, final int stringResId, Object value) {
        ((TextView)mView.findViewById(textViewResId)).setText(getString(stringResId, value));
    }
}
