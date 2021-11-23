package com.ksr.newsapp_restapi_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksr.newsapp_restapi_project.Models.Articles;
import com.ksr.newsapp_restapi_project.R;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    Articles articles;
    TextView textTitle,textAuthor,textTime,textDescription,textContent;
    ImageView imageNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        textTitle = findViewById(R.id.textDetailTitle);
        textAuthor = findViewById(R.id.textAuthorDetail);
        textTime = findViewById(R.id.textTimeDetail);
        textDescription = findViewById(R.id.textDescriptionDetail);
        textContent = findViewById(R.id.textContentDetail);
        imageNews = findViewById(R.id.imageNewsDetail);

        articles = (Articles) getIntent().getSerializableExtra("DATA");

        textTitle.setText(articles.getTitle());
        textAuthor.setText(articles.getAuthor());
        textTime.setText(articles.getPublishedAt());
        textDescription.setText(articles.getDescription());
        textContent.setText(articles.getContent());
        Picasso.get().load(articles.getUrlToImage()).into(imageNews);


    }
}