package com.velonovietnes.velonovietnes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ServiceInfoActivity extends Activity {

    TextView tvName, tvPhone, tvAdress, tvWebsite;
    DBAdapter myDB;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);

        lv = (ListView) findViewById(R.id.lvAvailableServices);

        Intent i = getIntent();
        String id = i.getStringExtra("serviceid");
        String name = i.getStringExtra("name");
        String adress = i.getStringExtra("adress");
        String phone = i.getStringExtra("phone");
        String website = i.getStringExtra("website");

        myDB = new DBAdapter(this);
        myDB.open();
        myDB.populateDB2();


        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAdress = (TextView) findViewById(R.id.tvAdress);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);



        tvName.setText(name);
        tvAdress.setText(adress);
        tvPhone.setText(phone);
        tvWebsite.setText(website);


        //Cursor that selected rows from the DBCursor getRow(String rowId)
        Cursor cursor = myDB.getRow(id);
        //Two arrays that gets the necessary info from DB and stores it
        String[] fromFieldNames = new String[]{DBAdapter.KEY_NAME2, DBAdapter.KEY_DIFF};
        int[] toViewIDs = new int[]{R.id.textView8, R.id.textView10};
        SimpleCursorAdapter myCursorAdapter;
        //The cursor adapter takes the custom list layout and table data so we can store it later in the custom list view
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list2_layout, cursor, fromFieldNames, toViewIDs, 0);
        //We make the listview use data from the cursor
        lv.setAdapter(myCursorAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_info, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
