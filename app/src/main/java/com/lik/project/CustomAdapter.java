package com.lik.project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<String> bookId, bookTitle, bookAuthor, bookPages, bookYears;

    public CustomAdapter(
            DataActivity mainActivity,
            Activity activity,
            ArrayList<String> bookId,
            ArrayList<String> bookTitle,
            ArrayList<String> bookAuthor,
            ArrayList<String> bookPages,
            ArrayList<String> bookYears) {
        this.activity = activity;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
        this.bookYears = bookYears;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.bookIdTxt.setText(bookId.get(position));
        holder.bookTitleTxt.setText(bookTitle.get(position));
        holder.bookAuthorTxt.setText(bookAuthor.get(position));
        holder.bookPagesTxt.setText(bookPages.get(position));
        holder.bookYearsTxt.setText(bookYears.get(position));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UpdateActivity.class);
                intent.putExtra("id", bookId.get(position));
                intent.putExtra("title", bookTitle.get(position));
                intent.putExtra("author", bookAuthor.get(position));
                intent.putExtra("pages", bookPages.get(position));
                intent.putExtra("years", bookYears.get(position));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookIdTxt, bookTitleTxt, bookAuthorTxt, bookPagesTxt, bookYearsTxt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdTxt = itemView.findViewById(R.id.book_id_txt);
            bookTitleTxt = itemView.findViewById(R.id.book_title_txt);
            bookAuthorTxt = itemView.findViewById(R.id.book_author_txt);
            bookPagesTxt = itemView.findViewById(R.id.book_pages_txt);
            bookYearsTxt = itemView.findViewById(R.id.book_years_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
