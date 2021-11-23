package com.ksr.newsapp_restapi_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ksr.newsapp_restapi_project.Models.Articles;
import com.ksr.newsapp_restapi_project.R;
import com.ksr.newsapp_restapi_project.Listeners.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<Articles> articles;
    private SelectListener listener;

    public NewsAdapter(Context context, List<Articles> articles, SelectListener listener) {
        this.context = context;
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_single_item, parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(itemView);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.textTitle.setText(articles.get(position).getTitle());
        holder.textSource.setText(articles.get(position).getSource().getName());

        if (articles.get(position).getUrlToImage() != null){
            Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imgHeadline);
        }

        holder.mainCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(articles.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle , textSource;
        ImageView imgHeadline;
        CardView mainCardView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSource = itemView.findViewById(R.id.textSource);
            imgHeadline = itemView.findViewById(R.id.imgHeadline);
            mainCardView = itemView.findViewById(R.id.mainCardView);

        }
    }


}
