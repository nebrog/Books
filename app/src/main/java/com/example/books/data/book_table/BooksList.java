package com.example.books.data.book_table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.books.data.author_table.AuthorList;

@Entity(foreignKeys = @ForeignKey(entity = AuthorList.class, parentColumns = "id", childColumns = "author_id"))
public class BooksList {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    @ColumnInfo(name = "author_id")
    public int authorId;

}