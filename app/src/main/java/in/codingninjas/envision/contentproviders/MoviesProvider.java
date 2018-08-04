package in.codingninjas.envision.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MoviesProvider extends ContentProvider {

    public static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int CODE_MOVIES = 100;
    public static final int CODE_MOVIE = 101;
    public static final int CODE_REVIES = 200;


    static {

        matcher.addURI(MoviesContract.AUTHORITY, MoviesContract.Movies.TABLE_NAME,CODE_MOVIES);
        matcher.addURI(MoviesContract.AUTHORITY, MoviesContract.Movies.TABLE_NAME + "/#",CODE_MOVIE);
        matcher.addURI(MoviesContract.AUTHORITY, MoviesContract.Reviews.TABLE_NAME,CODE_REVIES);

    }

    public static MoviesOpenHelper openHelper;


    public MoviesProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int match = matcher.match(uri);

        switch (match){

            case CODE_MOVIES:
                long moviewId = openHelper.getWritableDatabase().insert(MoviesContract.Movies.TABLE_NAME,null,values);
                return MoviesContract.Movies.buildUriForId(moviewId);
            case CODE_REVIES:
                long reviewId = openHelper.getWritableDatabase().insert(MoviesContract.Reviews.TABLE_NAME,null,values);
                return MoviesContract.Reviews.buildUriForId(reviewId);

        }

        return null;
    }

    @Override
    public boolean onCreate() {
       openHelper = MoviesOpenHelper.getInstance(getContext());
       return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        int match = matcher.match(uri);
        switch (match){

            case CODE_MOVIES:
                return openHelper.getReadableDatabase().query(MoviesContract.Movies.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case CODE_REVIES:
                return openHelper.getReadableDatabase().query(MoviesContract.Reviews.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case CODE_MOVIE:
                String id = uri.getLastPathSegment();
                String[] args = {id};
                return openHelper.getReadableDatabase().query(MoviesContract.Movies.TABLE_NAME,projection, MoviesContract.Movies._ID + " = ?",args,null,null,sortOrder);


        }
        return  null;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
