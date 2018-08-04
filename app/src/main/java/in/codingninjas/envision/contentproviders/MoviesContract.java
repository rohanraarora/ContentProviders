package in.codingninjas.envision.contentproviders;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String AUTHORITY = "in.codingninjas.envision.contentproviders";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static class Movies implements BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final String NAME = "name";
        public static final String OVERVIEW = "overview";

        public static final Uri CONENT_URI = BASE_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static Uri buildUriForId(long id){
            return ContentUris.withAppendedId(CONENT_URI,id);
        }
    }

    public static class Reviews implements BaseColumns {

        public static final String TABLE_NAME = "reviews";
        public static final String NAME = "name";
        public static final String REVIEW = "review";

        public static final Uri CONENT_URI = BASE_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static Uri buildUriForId(long id){
            return ContentUris.withAppendedId(CONENT_URI,id);
        }
    }

}
