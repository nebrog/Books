package com.example.books.data.author_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.books.data.book_table.BooksList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AuthorListDao {

        @Query("SELECT * FROM AuthorList")
        Flowable<List<AuthorList>> getAll();

        @Query("SELECT * FROM AuthorList WHERE id = :id")
        Single<AuthorList> getById(long id);

        @Insert
        Completable insert(List <AuthorList> authorList);
}
