package com.velonovietnes.velonovietnes;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class AvailableServicesActivity extends Activity {

    DBAdapter myDB;
    ListView lv;
    TextView serviceid, name, adress, phone, website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_services);
        lv = (ListView) findViewById(R.id.lvAvailableServices);
        openDB();
        myDB.deleteAll();
        myDB.populateDB2();
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
        Cursor cursor = myDB.getAllRows3();
        //Two arrays that gets the necessary info from DB and stores it
        String[] fromFieldNames = new String[]{DBAdapter.KEY_NAME2, DBAdapter.KEY_DIFF, DBAdapter.KEY_DESC};
        int[] toViewIDs = new int[] {R.id.textView11, R.id.textView12, R.id.textView13};
        SimpleCursorAdapter myCursorAdapter;
        //The cursor adapter takes the custom list layout and table data so we can store it later in the custom list view
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.list_layout3, cursor, fromFieldNames, toViewIDs, 0);
        //We make the listview use data from the cursor
        lv.setAdapter(myCursorAdapter);


    }


}
