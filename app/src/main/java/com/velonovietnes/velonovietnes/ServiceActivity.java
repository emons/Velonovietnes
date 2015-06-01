package com.velonovietnes.velonovietnes;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ServiceActivity extends Activity {

    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        openDB();
        myDB.deleteAll();
        myDB.populateDB();
        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }


    //Method to populate the list
    private void populateList(){
        //Cursor that returns all the rows from the DB
        Cursor cursor = myDB.getAllRows();
        //Two arrays that gets the necessary info from DB and stores it
        String[] fromFieldNames = new String[]{DBAdapter.KEY_NAME};
        int[] toViewIDs = new int[] {R.id.textView};
        SimpleCursorAdapter myCursorAdapter;
        //The cursor adapter takes the custom list layout and table data so we can store it later in the custom list view
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.list_layout, cursor, fromFieldNames, toViewIDs, 0);
        //Listview which is gonna display all the Serices
        ListView myList = (ListView) findViewById(R.id.lvService);
        //We make the listview use data from the cursor
        myList.setAdapter(myCursorAdapter);
    }
}
