package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2;

/**
   Created by DB-Project on 8/23/2017.
*/

class ReviewsDO {
    private String author, content;

    void setAuthorAndContent(String author, String content) {
        this.author = author;
        this.content = content;
    }

    String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
