package com.countriesinfo.models;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.countriesinfo.R;
import com.google.common.collect.ImmutableList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllCountriesFetcher {

    public static ImmutableList<BasicCountryInfo> fetchAllCountries(final Resources resources) {
        final List<BasicCountryInfo> basicCountryInfos = new ArrayList<>();
        try {
            final XmlResourceParser parser = resources.getXml(R.xml.country_lookup);
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if(eventType == XmlPullParser.START_TAG) {
                    String tag = parser.getName();
                    if (tag.equals("country")) {
                        final TypedArray a = resources.obtainAttributes(
                                Xml.asAttributeSet(parser), R.styleable.CountryLookupItem);
                        final int flag = a.getResourceId(R.styleable.CountryLookupItem_countryFlag, -1);
                        final int name = a.getResourceId(R.styleable.CountryLookupItem_countryName, -1);
                        final String code = a.getString(R.styleable.CountryLookupItem_countryCode);
                        if (flag != -1 && name != -1) {
                            basicCountryInfos.add(new BasicCountryInfo(flag, name, code));
                        }
                        a.recycle();
                    }
                }
                eventType = parser.next();
            }
            parser.close();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(basicCountryInfos);
    }
}
