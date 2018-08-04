package in.codingninjas.envision.contentproviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);

        fetchMovies();

       // fetchContacts();
    }

    public void onButtonPress(View view){
       addMovie();

    }

    public void fetchMovies(){
        Cursor cursor = getContentResolver().query(MoviesContract.Movies.CONENT_URI,null,null,null,null);
        if(cursor != null){
            items.clear();
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(MoviesContract.Movies.NAME));
                items.add(name);
            }
            adapter.notifyDataSetChanged();
        }
    }


    public void addMovie(){
        Movie movie = new Movie("Movie:" + UUID.randomUUID(),"overview");
        ContentValues values = new ContentValues();
        values.put(MoviesContract.Movies.NAME,movie.getName());
        values.put(MoviesContract.Movies.OVERVIEW,movie.getOverview());
        items.add(movie.getName());
        adapter.notifyDataSetChanged();

       getContentResolver().insert(MoviesContract.Movies.CONENT_URI,values);
    }

    public void startContactsActivity(){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent,1);
    }


    public void fetchContacts(){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        if(cursor != null){
            items.clear();
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                items.add(name);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if(cursor != null && cursor.moveToFirst()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            }
        }
    }
}
