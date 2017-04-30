package com.countriesinfo.models;

public class BasicCountryInfo {

    public final int mFlagResId;
    public final int mNameResId;
    public final String mCountryCode;

    public BasicCountryInfo(int flag, int name, String code) {
        mFlagResId = flag;
        mNameResId = name;
        mCountryCode = code;
    }

}
