package com.ksr.newsapp_restapi_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ksr.newsapp_restapi_project.API.ApiRequestManager;
import com.ksr.newsapp_restapi_project.API.OnFetchDataListener;
import com.ksr.newsapp_restapi_project.Adapters.NewsAdapter;
import com.ksr.newsapp_restapi_project.Models.Articles;
import com.ksr.newsapp_restapi_project.Models.NewsApiResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiRequestManager apiRequestManager = new ApiRequestManager(this);
        apiRequestManager.getNewsArticles(listener, "general", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
            showNews(list);
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Articles> list) {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, list);
        recyclerView.setAdapter(newsAdapter);

    }


}