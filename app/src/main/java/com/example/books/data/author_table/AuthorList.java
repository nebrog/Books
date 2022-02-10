package com.example.books.data.author_table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
    public class AuthorList {

        @PrimaryKey(autoGenerate = true)
        public int id;
        public String name;
        public Date birth_date;
    }