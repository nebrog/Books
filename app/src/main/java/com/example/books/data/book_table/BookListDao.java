package com.example.books.data.book_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface BookListDao {

    @Query("SELECT * FROM Bookslist")
    Flowable<List<BooksList>> getAll();

    @Query("SELECT * FROM bookslist WHERE id = :id")
    Single <BooksList> getById(long id);

    @Insert
    Completable insert(List <BooksList> booksList);

    @Update
    void update (BooksList booksList);


}