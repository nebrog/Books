package com.example.books.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.books.R;
import com.example.books.data.book_table.BooksList;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    public interface OnBookClickListener {
        void onBookListener(BooksList booksList);
    }


    private final LayoutInflater inflater;
    private final OnBookClickListener onClickListener;

    private final List<BooksList> booksLists = new ArrayList<>();


    public BookAdapter(Context context, OnBookClickListener onClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;


    }

    public void setChanges(List<BooksList> booksList) {
        booksLists.clear();
        booksLists.addAll(booksList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BooksList book = booksLists.get(position);
        holder.textView.setText(book.name);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onBookListener(book);



            }
        };
        holder.itemView.setOnClickListener(listener);


    }

    @Override
    public int getItemCount() {
        return booksLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.bookName);

        }

    }
}


