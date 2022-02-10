package com.example.books;

import android.app.Application;

import androidx.room.Room;

import com.example.books.data.BookDatabase;

public class App extends Application {

    public static App instance;
    private BookDatabase bookDatabase;
    private AuthorDatabase authorDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        bookDatabase = Room.databaseBuilder(this, BookDatabase.class, "bookDatabase")
                .build();

    }

    public static App getInstance() {
        return instance;
    }

    public BookDatabase getDatabase() {
        return bookDatabase;
    }

}
