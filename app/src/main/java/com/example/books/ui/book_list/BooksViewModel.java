package com.example.books.ui.book_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.books.App;
import com.example.books.data.BookDatabase;
import com.example.books.data.author_table.AuthorList;
import com.example.books.data.book_table.BooksList;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BooksViewModel extends ViewModel {
    public LiveData<List<BooksList>> getBookList() {
        BookDatabase bookDatabase = App.getInstance().getDatabase();
        Flowable<List<BooksList>> d = bookDatabase.bookListDao().getAll()
                .observeOn(AndroidSchedulers.mainThread());
        return LiveDataReactiveStreams.fromPublisher(d);


    }
}