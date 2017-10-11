/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.PopularMovies_V2.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database.
 */
public class pm2_Contract {

    static final String pm2_CONTENT_AUTHORITY = "unitedapps.com.googleandroidcourses.pm2";
    private static final Uri pm2_BASE_CONTENT_URI = Uri.parse("content://" + pm2_CONTENT_AUTHORITY);
    static final String pm2_PATH_MOVIES = "movies";
    static final String pm2_PATH_FAV = "favorite";


    public static final class pm2_MoviesEntry implements BaseColumns {
        static final String pm2_TABLE_NAME = "movies";
        public static final String pm2_COLUMN_MOVIE_ID = "movie_id";
        public static final String pm2_COLUMN_MOVIE_NAME = "name";
        public static final String pm2_COLUMN_MOVIE_RATE = "rate";
        public static final String pm2_COLUMN_MOVIE_YEAR = "year";
        public static final String pm2_COLUMN_MOVIE_SYS = "sys";
        public static final String pm2_COLUMN_MOVIE_POSTER = "poster";

        public static final String pm2_COLUMN_MOVIE_REVIEWS_A = "reviews_author";
        public static final String pm2_COLUMN_MOVIE_REVIEWS_C = "reviews_content";
        public static final String pm2_COLUMN_MOVIE_TRAILERS = "trailers";
        public static final String pm2_COLUMN_MOVIE_LENGTH = "length";
        public static final String pm2_COLUMN_MOVIE_FAV = "fav";
        public static final String pm2_COLUMN_MOVIE_RATED = "rated";

        static final Uri pm2_CONTENT_URI =
                pm2_BASE_CONTENT_URI.buildUpon().appendPath(pm2_PATH_MOVIES).build();

        static final String pm2_CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + pm2_CONTENT_AUTHORITY + "/" + pm2_PATH_MOVIES;

        public static Uri pm2_buildMoviesUri(long id) {
            return ContentUris.withAppendedId(pm2_CONTENT_URI, id);
        }

    }
}