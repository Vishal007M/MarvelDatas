package com.vsl.marveldatas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static Retrofit retrofit;

    public static Retrofit getRetroClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstantData.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
