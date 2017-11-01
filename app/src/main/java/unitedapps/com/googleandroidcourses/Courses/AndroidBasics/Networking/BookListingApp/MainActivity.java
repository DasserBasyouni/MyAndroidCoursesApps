package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.BookListingApp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by dasse on 27-Oct-17.
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BooksDataObject>> {

    private ListView books_lv;
    private final int BOOKS_LOADER_ID = 444;
    private String mURL = null;
    ArrayList<BooksDataObject> booksDataObjectArrayList;
    BooksAdapter booksAdapter;
    TextView empty_view_tv;
    ProgressBar searching_pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ab_n_bla_activity_main);

        books_lv = findViewById(R.id.ab_n_bla_books_lv);
        empty_view_tv = findViewById(R.id.ab_n_bla_empty_view_tv);
        searching_pb = findViewById(R.id.ab_n_bla_searching_pb);

        booksDataObjectArrayList = new ArrayList<>();
        booksAdapter = new BooksAdapter(this, booksDataObjectArrayList);

        books_lv.setAdapter(booksAdapter);

        final TextInputLayout book_search_til = findViewById(R.id.ab_n_bla_book_search_til);
        Button search_btn = findViewById(R.id.ab_n_bla_search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getEditText = book_search_til.getEditText();
                if(getEditText != null) {

                    ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    assert cm != null;
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

                    // this if for checking internet connection
                    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        empty_view_tv.setVisibility(View.GONE);
                        mURL = "https://www.googleapis.com/books/v1/volumes?q=" +
                                getSearchFormat(getEditText.getText().toString()) +
                                "=handleResponse";
                        searching_pb.setVisibility(View.VISIBLE);
                        getLoaderManager().initLoader(BOOKS_LOADER_ID, null, MainActivity.this);
                    }else {
                        empty_view_tv.setText(getString(R.string.ab_n_eq4_no_internet));
                    }
                }
            }

            private String getSearchFormat(String UserInputData) {
                // this replace space with "+" sign to be able to use it in the url
                return (UserInputData.trim()).replace(" ", "+");
            }
        });
    }


    @Override
    public Loader<List<BooksDataObject>> onCreateLoader(int i, Bundle bundle) {
        return new BooksLoader(this, mURL);
    }

    @Override
    public void onLoadFinished(Loader<List<BooksDataObject>> loader, List<BooksDataObject> booksDataObjects) {
        booksDataObjectArrayList.clear();
        booksDataObjectArrayList.addAll(booksDataObjects);
        searching_pb.setVisibility(View.GONE);
        empty_view_tv.setText(getString(R.string.ab_n_bla_empty_search));
        books_lv.setEmptyView(empty_view_tv);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BooksDataObject>> loader) {
        booksDataObjectArrayList.clear();
    }
}
