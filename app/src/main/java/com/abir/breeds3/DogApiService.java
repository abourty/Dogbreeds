package com.abir.breeds3;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DogApiService {
    @GET("/v1/breeds")
    Call<List<Dog>>
    getDogs(@Query("breed_group") String breedGroup);

}
