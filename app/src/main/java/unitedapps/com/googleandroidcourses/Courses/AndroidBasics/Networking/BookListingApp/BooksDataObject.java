package unitedapps.com.googleandroidcourses.Courses.AndroidBasics.Networking.BookListingApp;

import android.graphics.Bitmap;

/**
   Created by dasse on 28-Oct-17.
 */

class BooksDataObject {
    private String bNameAndYear, bAuthors;
    private int bNumberOfPages;
    private String bPreviewLinks;
    private Bitmap bBitmap;

    BooksDataObject(String bNameAndYear, String bAuthors, int bNumberOfPages, String bPreviewLinks, Bitmap bBitmap) {
        this.bNameAndYear = bNameAndYear;
        this.bAuthors = bAuthors;
        this.bNumberOfPages = bNumberOfPages;
        this.bPreviewLinks = bPreviewLinks;
        this.bBitmap = bBitmap;
    }

    String getBookNameAndYear() {
        return bNameAndYear;
    }

    String getBookAuthor() {
        return bAuthors;
    }

    int getNumberOfPages() {
        return bNumberOfPages;
    }

    String getPreviewLink() {
        return bPreviewLinks;
    }

    Bitmap getbBitmap() {
        return bBitmap;
    }
}
