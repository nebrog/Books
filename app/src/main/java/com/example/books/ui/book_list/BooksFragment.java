package com.example.books.ui.book_list;

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
import com.example.books.data.book_table.BookListDao;

public class BooksFragment extends Fragment {

    private BooksViewModel mViewModel;

    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.books_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        BookDatabase bookDatabase = App.getInstance().getDatabase();
        BookListDao bookListDao = bookDatabase.bookListDao;




    }


}