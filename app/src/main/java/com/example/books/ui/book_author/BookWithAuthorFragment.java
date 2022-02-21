package com.example.books.ui.book_author;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.books.R;
import com.example.books.data.author_table.AuthorList;
import com.example.books.data.book_table.BooksList;

public class BookWithAuthorFragment extends Fragment {

    private BookWithAuthorViewModel mViewModel;

    Button buttonSave;
    Button buttonEdit;
    EditText description;

    public static BookWithAuthorFragment newInstance() {
        return new BookWithAuthorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.book_with_author_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int authorId = getArguments().getInt("authorId");
        int bookId = getArguments().getInt("idBook");
        findViews(view);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setFocusable(true);
                description.setFocusableInTouchMode(true);
                buttonEdit.setVisibility(View.GONE);
                buttonSave.setVisibility(View.VISIBLE);
            }
        };
        buttonEdit.setOnClickListener(listener);

        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setFocusable(false);
                description.setCursorVisible(false);
                buttonSave.setVisibility(View.GONE);
                buttonEdit.setVisibility(View.VISIBLE);
                mViewModel.Update(description.getText().toString());



            }
        };
        buttonSave.setOnClickListener(saveListener);

        mViewModel = new ViewModelProvider(this).get(BookWithAuthorViewModel.class);
        mViewModel.getAuthorList(authorId,bookId).observe(this, new Observer<Pair<AuthorList, BooksList>>() {
            @Override
            public void onChanged(Pair<AuthorList, BooksList> pair) {
                setData(view,pair.second, pair.first);
            }
        });


    }
    private void findViews(View view){
        buttonEdit = view.findViewById(R.id.button_edit);
        description = view.findViewById(R.id.description);
        buttonSave = view.findViewById(R.id.button_save);
    }
    private void setData(View view, BooksList booksList, AuthorList authorList){
        TextView bookName = view.findViewById(R.id.book);
        TextView author = view.findViewById(R.id.author);
        bookName.setText(booksList.name);
        author.setText(authorList.name);
        description.setText(booksList.description);
        description.setFocusable(false);
        description.setCursorVisible(false);










    }


}