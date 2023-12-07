package com.vsl.marveldatas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("demos/marvel/")
    Call<List<DataModel>> getAllDataApi();

}
