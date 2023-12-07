package com.vsl.marveldatas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.OnItemClickListener {

    RecyclerView recyclerView;

    List<DataModel> dataModels = new ArrayList<>();
    DataViewModel dataViewModel;
    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);

        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        dataAdapter = new DataAdapter((Context) this, this,dataModels);
        recyclerView.setAdapter(dataAdapter);

        getDataListApi();

    }

    private void getDataListApi() {

        dataViewModel.getGetAllData().observe(this, new Observer<Resource<List<DataModel>>>() {
            @Override
            public void onChanged(Resource<List<DataModel>> listResource) {
                try {

                    if (listResource.data != null){
                        loadData(listResource.data);
                    }else {
                        Toast.makeText(MainActivity.this, ConstantData.SOMETHING_WRONG, Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        dataViewModel.setGetAllData();
        allData(dataModels);


    }

    private void allData(List<DataModel> dataModels) {
        dataModels.clear();
        dataModels.addAll(dataModels);
        dataAdapter.notifyDataSetChanged();
    }

    private void loadData(List<DataModel> data) {
        dataModels = data;
        dataAdapter.updateDataList(data);
    }


    @Override
    public void onItemClick(int position) {

        DataModel data = dataModels.get(position);

        Dialog popUpDialog = new Dialog(this,R.style.popup_dialog);
        popUpDialog.setContentView(R.layout.popup_data_marvel);
        popUpDialog.show();

        ImageView imageUrl = popUpDialog.findViewById(R.id.imageurl);

        TextView name = popUpDialog.findViewById(R.id.name);
        TextView realname = popUpDialog.findViewById(R.id.realname);
        TextView team = popUpDialog.findViewById(R.id.team);
        TextView firstappearance = popUpDialog.findViewById(R.id.firstappearance);
        TextView createdby = popUpDialog.findViewById(R.id.createdby);
        TextView publisher = popUpDialog.findViewById(R.id.publisher);
        TextView bio = popUpDialog.findViewById(R.id.bio);

        Picasso.get().load(data.getImageurl()).into(imageUrl);

        name.setText(data.getName());
        realname.setText(data.getRealname());
        team.setText(data.getTeam());
        firstappearance.setText(data.getFirstappearance());
        createdby.setText(data.getCreatedby());
        publisher.setText(data.getPublisher());
        bio.setText(data.getBio());

    }
}