package com.vsl.marveldatas;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {

    private MutableLiveData<Resource<List<DataModel>>> getAllData;

    public DataViewModel() {
        getAllData = new MediatorLiveData<>();
    }

    public MutableLiveData<Resource<List<DataModel>>> getGetAllData() {
        return getAllData;
    }

    public void setGetAllData (){
        ApiServices apiServices = RetroInstance.getRetroClient().create(ApiServices.class);
        Call<List<DataModel>> call = apiServices.getAllDataApi();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                try {
                    if (response != null){
                        List<DataModel> dataModels = response.body();
                        getAllData.setValue(Resource.success(dataModels));
                    }
                    else {
                        getAllData.setValue(Resource.error(ConstantData.SOMETHING_WRONG,null));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                getAllData.setValue(Resource.error(t.getMessage(),null));
            }
        });

    }

}
