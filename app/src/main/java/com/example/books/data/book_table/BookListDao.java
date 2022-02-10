package com.example.books.data.book_table;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookListDao {

    @Query("SELECT * FROM Bookslist")
    List<BooksList> getAll();

    @Query("SELECT * FROM bookslist WHERE id = :id")
    BooksList getById(long id);


}