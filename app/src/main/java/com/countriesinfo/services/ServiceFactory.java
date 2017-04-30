package com.countriesinfo.services;

import retrofit.RestAdapter;

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        final T service = restAdapter.create(clazz);

        return service;
    }
}
