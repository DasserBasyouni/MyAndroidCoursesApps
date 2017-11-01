package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.BookListingApp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * Created by dasse on 27-Oct-17.
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BooksDataObject>> {

    private ListView books_lv;
    private final int BOOKS_LOADER_ID = 444;
    private String mURL = null;
    private ArrayList<BooksDataObject> booksDataObjectArrayList;
    private BooksAdapter booksAdapter;
    private TextView empty_view_tv;
    private ProgressBar searching_pb;
    private Parcelable state, state1;
    private EditText book_search_et;

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

        book_search_et = findViewById(R.id.ab_n_bla_book_search_et);
        Button search_btn = findViewById(R.id.ab_n_bla_search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveInternetConnection()) {
                    empty_view_tv.setVisibility(View.GONE);
                    mURL = "https://www.googleapis.com/books/v1/volumes?q=" +
                            getSearchFormat(book_search_et.getText().toString()) +
                            "=handleResponse";
                    searching_pb.setVisibility(View.VISIBLE);
                    getLoaderManager().initLoader(BOOKS_LOADER_ID, null, MainActivity.this);

                } else {
                    empty_view_tv.setText(getString(R.string.ab_n_eq4_no_internet));
                }
            }
        });

        if (getLoaderManager().getLoader(BOOKS_LOADER_ID) != null && getLoaderManager().getLoader(BOOKS_LOADER_ID).isStarted()) {
            Log.d("Z_loader", String.valueOf(getLoaderManager().getLoader(BOOKS_LOADER_ID).isStarted()));
            mURL = "https://www.googleapis.com/books/v1/volumes?q=" +
                    getSearchFormat(book_search_et.getText().toString()) +
                    "=handleResponse";
            getLoaderManager().initLoader(BOOKS_LOADER_ID, null, MainActivity.this);
        }
    }

    private String getSearchFormat(String UserInputData) {
        // this replace space with "+" sign to be able to use it in the url
        return (UserInputData.trim()).replace(" ", "+");
    }

    public boolean haveInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public Loader<List<BooksDataObject>> onCreateLoader(int i, Bundle bundle) {
        Log.d("Z_link", mURL);
        return new BooksLoader(this, mURL);
    }

    @Override
    public void onLoadFinished(Loader<List<BooksDataObject>> loader, List<BooksDataObject> booksDataObjects) {
        books_lv.setVisibility(View.VISIBLE);
        Log.d("Z_", "loaded");
        booksDataObjectArrayList.clear();

        if (booksDataObjects != null && !booksDataObjects.isEmpty())
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
