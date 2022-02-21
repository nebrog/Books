package com.example.books.ui.book_list;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.books.MainActivity;
import com.example.books.R;
import com.example.books.data.BookAdapter;
import com.example.books.data.book_table.BooksList;
import com.example.books.ui.book_author.BookWithAuthorFragment;
import com.example.books.ui.tools.ItemOffsetDecoration;

import java.util.List;

public class BooksFragment extends Fragment {

    private BooksViewModel mViewModel;


    BookAdapter.OnBookClickListener onBookClickListener = new BookAdapter.OnBookClickListener() {
        @Override
        public void onBookListener(BooksList booksList) {
            Bundle args = new Bundle();
            args.putInt("authorId",booksList.authorId);
            args.putInt("idBook", booksList.id);
            NavHostFragment.findNavController(BooksFragment.this).navigate(R.id.bookWithAuthor,args);

        }
    };


    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.books_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BookAdapter adapter = new BookAdapter(getContext(),onBookClickListener);
        recyclerView.setAdapter(adapter);

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getContext(), R.dimen.recycler_item_offset);
        recyclerView.addItemDecoration(itemOffsetDecoration);


        mViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        mViewModel.getBookList().observe(this, new Observer<List<BooksList>>() {
            @Override
            public void onChanged(List<BooksList> booksLists) {
                adapter.setChanges(booksLists);
            }
        });


    }


}