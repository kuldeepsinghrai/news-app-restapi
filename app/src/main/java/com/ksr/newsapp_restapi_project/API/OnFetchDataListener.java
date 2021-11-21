package com.ksr.newsapp_restapi_project.API;

import com.ksr.newsapp_restapi_project.Models.Articles;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse>{
    void onFetchData(List<Articles> list, String message);
    void onError(String message);
}
