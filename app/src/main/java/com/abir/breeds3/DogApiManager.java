package com.abir.breeds3;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogApiManager {
    private static final String BASE_URL = "https://api.thedogapi.com";
    private static final String DOG_IMAGES_API_URL = "https://api.thedogapi.com/v1/images/";

    private static DogApiService dogApiService;

    public static DogApiService getDogApiService() {
        if (dogApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            dogApiService = retrofit.create(DogApiService.class);
        }
        return dogApiService;
    }
}
