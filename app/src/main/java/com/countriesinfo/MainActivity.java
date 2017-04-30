package com.countriesinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.countriesinfo.models.CountryData;

public class MainActivity extends AppCompatActivity implements OnReceiveCountryDetailInfoListener, OnBackStackChangedListener {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);

        if (findViewById(R.id.fragment_container) != null) {
            final AllCountriesFragment allCountriesFragment = new AllCountriesFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, allCountriesFragment).commit();
        }
    }

    @Override
    public void onReceiveCountryDetailInfo(CountryData countryData) {
        final CountryDetailFragment countryDetailFragment = CountryDetailFragment.newInstance(countryData);

        final FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right);
        transaction.replace(R.id.fragment_container, countryDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mFragmentManager.getBackStackEntryCount() > 0) {
                    mFragmentManager.popBackStack();
                }
                updateActionBar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackStackChanged() {
        updateActionBar();
    }

    public void updateActionBar(){
        final boolean hasBackStack = mFragmentManager.getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackStack);
        if (!hasBackStack) {
            setTitle(R.string.app_name);
        }
    }
}
