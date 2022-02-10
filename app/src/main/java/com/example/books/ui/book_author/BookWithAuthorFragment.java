package com.example.books.ui.book_author;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.App;
import com.example.books.R;
import com.example.books.data.BookDatabase;
import com.example.books.data.author_table.AuthorListDao;

public class BookWithAuthorFragment extends Fragment {

    private BookWithAuthorViewModel mViewModel;

    public static BookWithAuthorFragment newInstance() {
        return new BookWithAuthorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.book_with_author_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookWithAuthorViewModel.class);
        BookDatabase bookDatabase = App.getInstance().getDatabase();
        AuthorListDao authorListDao = bookDatabase.authorListDao;



    }
}