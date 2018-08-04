package in.codingninjas.envision.contentproviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesOpenHelper extends SQLiteOpenHelper {

    private static MoviesOpenHelper instance;

    public static MoviesOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new MoviesOpenHelper(context.getApplicationContext());
        }

        return instance;
    }

    private MoviesOpenHelper(Context context) {
        super(context, "movies_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  moviesSql = "CREATE TABLE " + MoviesContract.Movies.TABLE_NAME + " ( " +
                MoviesContract.Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.Movies.NAME + " TEXT, " +
                MoviesContract.Movies.OVERVIEW + " TEXT)";

        String  reviewsSql = "CREATE TABLE " + MoviesContract.Reviews.TABLE_NAME + " ( " +
                MoviesContract.Reviews._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesContract.Reviews.NAME + " TEXT, " +
                MoviesContract.Reviews.REVIEW + " TEXT)";

        sqLiteDatabase.execSQL(moviesSql);
        sqLiteDatabase.execSQL(reviewsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
