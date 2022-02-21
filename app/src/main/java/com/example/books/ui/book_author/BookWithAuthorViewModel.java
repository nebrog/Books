package com.example.books.ui.book_author;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.App;
import com.example.books.data.BookDatabase;
import com.example.books.data.author_table.AuthorList;
import com.example.books.data.author_table.AuthorListDao;
import com.example.books.data.book_table.BookListDao;
import com.example.books.data.book_table.BooksList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BookWithAuthorViewModel extends ViewModel {

    private final BookListDao bookListDao = App.getInstance().getDatabase().bookListDao();
    private final AuthorListDao authorListDao = App.getInstance().getDatabase().authorListDao();

    @Nullable
    private BooksList book;

    public LiveData<Pair<AuthorList, BooksList>> getAuthorList(int idAuthor, int idBook) {

        Single<AuthorList> d = authorListDao.getById(idAuthor)
                .observeOn(AndroidSchedulers.mainThread());
        Single<BooksList> booksListSingle = bookListDao.getById(idBook);
        Single<Pair<AuthorList, BooksList>> zip = Single.zip(d, booksListSingle, new BiFunction<AuthorList, BooksList, Pair<AuthorList, BooksList>>() {
            @NonNull
            @Override
            public Pair<AuthorList, BooksList> apply(@NonNull AuthorList authorList, @NonNull BooksList booksList) throws Exception {
                Pair<AuthorList, BooksList> pair = new Pair<>(authorList, booksList);
                book = pair.second;
                return pair;
            }
        }).subscribeOn(Schedulers.io());
        return LiveDataReactiveStreams.fromPublisher(zip.toFlowable());

    }

    public void Update(String description) {

        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                if (book != null) {
                    book.description = description;
                    bookListDao.update(book);
                }

            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

}