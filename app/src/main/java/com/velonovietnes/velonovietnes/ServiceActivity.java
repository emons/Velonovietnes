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


public class ServiceActivity extends Activity {

    DBAdapter myDB;
    ListView lv;
    TextView serviceid, name, adress, phone, website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        lv = (ListView) findViewById(R.id.lvService);
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
        String[] fromFieldNames = new String[]{DBAdapter.KEY_NAME, DBAdapter.KEY_ADRESS, DBAdapter.KEY_PHONE, DBAdapter.KEY_WEBSITE, DBAdapter.KEY_ROWID};
        int[] toViewIDs = new int[] {R.id.textView, R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6};
        SimpleCursorAdapter myCursorAdapter;
        //The cursor adapter takes the custom list layout and table data so we can store it later in the custom list view
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.list_layout, cursor, fromFieldNames, toViewIDs, 0);
        //We make the listview use data from the cursor
        lv.setAdapter(myCursorAdapter);

        //Clicking on a service in the list, takes you to an activity which shows more info about that service center
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                website = (TextView) view.findViewById(R.id.textView5);
                name = (TextView) view.findViewById(R.id.textView);
                adress = (TextView) view.findViewById(R.id.textView3);
                phone = (TextView) view.findViewById(R.id.textView4);
                serviceid = (TextView) view.findViewById(R.id.textView6);

                String name_val = name.getText().toString();
                String adress_val = adress.getText().toString();
                String website_val = website.getText().toString();
                String phone_val = phone.getText().toString();
                String serviceid_val = serviceid.getText().toString();

                Intent info_intent = new Intent(getApplicationContext(),
                        ServiceInfoActivity.class);
                info_intent.putExtra("name", name_val);
                info_intent.putExtra("adress", adress_val);
                info_intent.putExtra("website", website_val);
                info_intent.putExtra("phone", phone_val);
                info_intent.putExtra("serviceid", serviceid_val);
                startActivity(info_intent);
            }
        });
    }


}
