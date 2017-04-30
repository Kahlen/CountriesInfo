package com.countriesinfo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CountryData implements Parcelable {

    private String name;
    private String capital;
    private int population;
    private double area;
    private String region;
    private String demonym;
    private String nativeName;
    private Language[] languages;
    private Currency[] currencies;
    private int imageResId;

    public CountryData(Parcel parcel) {
        name = parcel.readString();
        capital = parcel.readString();
        population = parcel.readInt();
        area = parcel.readDouble();
        region = parcel.readString();
        demonym = parcel.readString();
        nativeName = parcel.readString();
        parcel.readTypedArray(languages, Language.CREATOR);
        parcel.readTypedArray(currencies, Currency.CREATOR);
        imageResId = parcel.readInt();
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public String getRegion() {
        return region;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getLanguage() {
        final StringBuilder sb = new StringBuilder();
        for (Language l: languages) {
            sb.append(l.name + ", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length()-2);
        }
        return sb.toString();
    }

    public String getCurrencies() {
        final StringBuilder sb = new StringBuilder();
        for (Currency c: currencies) {
            sb.append(c.code + " " + c.symbol + ", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length()-2);
        }
        return sb.toString();
    }

    public void setImageResId(int resId) {
        imageResId = resId;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(capital);
        parcel.writeInt(population);
        parcel.writeDouble(area);
        parcel.writeString(region);
        parcel.writeString(demonym);
        parcel.writeString(nativeName);
        parcel.writeTypedArray(languages, 0);
        parcel.writeTypedArray(currencies, 0);
        parcel.writeInt(imageResId);
    }

    public static final Creator CREATOR = new Creator() {
        public CountryData createFromParcel(Parcel in) {
            return new CountryData(in);
        }

        public CountryData[] newArray(int size) {
            return new CountryData[size];
        }
    };

    static class Language implements Parcelable {
        String name;

        public Language(Parcel parcel) {
            name = parcel.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
        }

        public static final Creator CREATOR = new Creator() {
            public Language createFromParcel(Parcel in) {
                return new Language(in);
            }

            public Language[] newArray(int size) {
                return new Language[size];
            }
        };
    }

    static class Currency implements Parcelable {
        String code;
        String symbol;

        public Currency(Parcel parcel) {
            code = parcel.readString();
            symbol = parcel.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(code);
            parcel.writeString(symbol);
        }

        public static final Creator CREATOR = new Creator() {
            public Currency createFromParcel(Parcel in) {
                return new Currency(in);
            }

            public Currency[] newArray(int size) {
                return new Currency[size];
            }
        };
    }
}
