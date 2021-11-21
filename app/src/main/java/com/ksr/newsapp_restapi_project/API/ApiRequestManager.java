package com.ksr.newsapp_restapi_project.API;

import android.content.Context;
import android.widget.Toast;

import com.ksr.newsapp_restapi_project.Models.NewsApiResponse;
import com.ksr.newsapp_restapi_project.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRequestManager {
    Context context;


    // API -  https://newsapi.org/v2/top-headlines?country=in&apiKey=7956ce74580a44c098224875d7c51707

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsArticles(OnFetchDataListener listener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callArticles("in", category, query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(), response.message());

                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed!!");

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ApiRequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi {

        @GET("top-headlines")
        Call<NewsApiResponse> callArticles(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }

}
