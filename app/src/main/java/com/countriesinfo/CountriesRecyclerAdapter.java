package com.countriesinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.countriesinfo.models.BasicCountryInfo;
import com.countriesinfo.models.CountryData;
import com.countriesinfo.services.CountryFetcherService;
import com.countriesinfo.services.ServiceFactory;
import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.jakewharton.rxbinding.view.RxView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CountriesRecyclerAdapter
        extends RecyclerView.Adapter<CountriesRecyclerAdapter.CountryViewHolder>
        implements SectionTitleProvider {

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public CountryViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.flag_image_view);
            mTextView = (TextView) itemView.findViewById(R.id.country_name_text_view);
        }
    }

    private ImmutableList<BasicCountryInfo> mBasicCountryInfos;
    private Context mContext;
    private OnReceiveCountryDetailInfoListener mListener;

    public CountriesRecyclerAdapter(final Context context,
                                    final ImmutableList<BasicCountryInfo> countries,
                                    final OnReceiveCountryDetailInfoListener listener) {
        mContext = context;
        mListener = listener;
        mBasicCountryInfos = countries;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {
        final BasicCountryInfo country = mBasicCountryInfos.get(position);
        holder.mImageView.setImageResource(country.mFlagResId);
        holder.mTextView.setText(country.mNameResId);

        RxView.clicks(holder.itemView)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        final CountryFetcherService service = ServiceFactory.createRetrofitService(
                                                                CountryFetcherService.class,
                                                                CountryFetcherService.SERVICE_ENDPOINT);
                        service.getCountryData(country.mCountryCode)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<CountryData>() {
                                    @Override
                                    public final void onCompleted() {
                                    }

                                    @Override
                                    public final void onError(Throwable e) {
                                        Toast.makeText(mContext, R.string.check_connection, Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public final void onNext(CountryData response) {
                                        response.setImageResId(country.mFlagResId);
                                        mListener.onReceiveCountryDetailInfo(response);
                                    }
                                });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mBasicCountryInfos.size();
    }

    @Override
    public String getSectionTitle(int position) {
        final String countryName = mContext.getString(mBasicCountryInfos.get(position).mNameResId);
        return Strings.isNullOrEmpty(countryName)? "" : countryName.substring(0, 1);
    }
}
