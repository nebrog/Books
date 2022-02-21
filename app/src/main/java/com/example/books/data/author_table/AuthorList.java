package com.example.books.data.author_table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class AuthorList {
    public AuthorList(String name, long birth_date) {
        this.name = name;
        this.birth_date = birth_date;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public long birth_date;

    @Override
    public String toString() {
        return "AuthorList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}