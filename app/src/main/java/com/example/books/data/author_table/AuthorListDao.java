package com.example.books.data.author_table;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;
@Dao
public interface AuthorListDao {

        @Query("SELECT * FROM AuthorList")
        List<AuthorList> getAll();

        @Query("SELECT * FROM AuthorList WHERE id = :id")
        AuthorList getById(long id);
}
