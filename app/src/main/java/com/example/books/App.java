package com.example.books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.books.data.BookDatabase;
import com.example.books.data.author_table.AuthorList;
import com.example.books.data.book_table.BooksList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {

    public static App instance;
    private BookDatabase bookDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "bookDatabase")

                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        List <AuthorList> authorList = new ArrayList<>();
                        authorList.add(new AuthorList("Alex", 827971200000L));
                        authorList.add(new AuthorList("Dima", 827971200000L));
                        authorList.add(new AuthorList("Vlad", 827971200000L));
                        authorList.add(new AuthorList("Stas", 827971200000L));
                        authorList.add(new AuthorList("Sasha", 827971200000L));
                        authorList.add(new AuthorList("Misha", 827971200000L));
                        authorList.add(new AuthorList("Nick", 827971200000L));
                        authorList.add(new AuthorList("Mark", 827971200000L));
                        authorList.add(new AuthorList("Felix", 827971200000L));
                        authorList.add(new AuthorList("John", 827971200000L));
                        List<BooksList> booksLists = new ArrayList<>();
                        booksLists.add(new BooksList("Aivengo","description",1));
                        booksLists.add(new BooksList("Aivengo2","description",1));
                        booksLists.add(new BooksList("Gods","description",3));
                        booksLists.add(new BooksList("Moscow","description",4));
                        booksLists.add(new BooksList("Berlin","description",5));
                        booksLists.add(new BooksList("Master","description",6));
                        booksLists.add(new BooksList("Doctor","description",7));
                        booksLists.add(new BooksList("Rose","description",8));
                        booksLists.add(new BooksList("Aizek","description",9));
                        booksLists.add(new BooksList("Lol","description",10));
                        bookDatabase.authorListDao()
                                .insert(authorList)
                                .andThen(bookDatabase.bookListDao().insert(booksLists))
                                .subscribeOn(Schedulers.io())
                                .subscribe();
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        db.disableWriteAheadLogging();

                    }
                })
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public BookDatabase getDatabase() {
        return bookDatabase;
    }

}
