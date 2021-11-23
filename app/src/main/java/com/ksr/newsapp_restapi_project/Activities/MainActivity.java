package com.ksr.newsapp_restapi_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ksr.newsapp_restapi_project.API.ApiRequestManager;
import com.ksr.newsapp_restapi_project.API.OnFetchDataListener;
import com.ksr.newsapp_restapi_project.Adapters.NewsAdapter;
import com.ksr.newsapp_restapi_project.Models.Articles;
import com.ksr.newsapp_restapi_project.Models.NewsApiResponse;
import com.ksr.newsapp_restapi_project.R;
import com.ksr.newsapp_restapi_project.Listeners.SelectListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener , View.OnClickListener {
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ProgressDialog dialog;
    Button btnBusiness, btnEntertainment,btnGeneral,btnHealth,btnScience,btnSports,btnTechnology;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Fetching News Articles...");
        dialog.show();


        btnBusiness = findViewById(R.id.btnBusiness);
        btnEntertainment = findViewById(R.id.btnEntertainment);
        btnGeneral = findViewById(R.id.btnGeneral);
        btnHealth = findViewById(R.id.btnHealth);
        btnScience = findViewById(R.id.btnScience);
        btnSports = findViewById(R.id.btnSports);
        btnTechnology = findViewById(R.id.btnTechnology);

        btnBusiness.setOnClickListener(this);
        btnEntertainment.setOnClickListener(this);
        btnGeneral.setOnClickListener(this);
        btnHealth.setOnClickListener(this);
        btnScience.setOnClickListener(this);
        btnSports.setOnClickListener(this);
        btnTechnology.setOnClickListener(this);



        ApiRequestManager apiRequestManager = new ApiRequestManager(this);
        apiRequestManager.getNewsArticles(listener, "general", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Articles> list) {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, list,this);
        recyclerView.setAdapter(newsAdapter);

    }


    @Override
    public void OnNewsClicked(Articles articles) {
        Intent intent = new Intent(MainActivity.this,NewsDetailsActivity.class);
        intent.putExtra("DATA", articles);

        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Fetching News Articles of "+category+" ...");
        dialog.show();
        ApiRequestManager apiRequestManager = new ApiRequestManager(this);
        apiRequestManager.getNewsArticles(listener, category, null);
    }
}