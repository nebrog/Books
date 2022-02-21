package com.example.books.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.books.data.author_table.AuthorList;
import com.example.books.data.author_table.AuthorListDao;
import com.example.books.data.book_table.BookListDao;
import com.example.books.data.book_table.BooksList;

@Database(entities = {BooksList.class, AuthorList.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookListDao bookListDao();
    public abstract AuthorListDao authorListDao();
}

