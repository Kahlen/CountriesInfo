package com.countriesinfo.services;

import com.countriesinfo.models.CountryData;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface CountryFetcherService {
    String SERVICE_ENDPOINT = "https://restcountries.eu/rest/v2/alpha";

    @GET("/{country}")
    Observable<CountryData> getCountryData(@Path("country") String country);

}
