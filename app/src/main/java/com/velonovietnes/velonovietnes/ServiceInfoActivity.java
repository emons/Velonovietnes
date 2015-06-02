package com.velonovietnes.velonovietnes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ServiceInfoActivity extends Activity {

    TextView tvName, tvPhone, tvAdress, tvWebsite;
    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);

        myDB = new DBAdapter(this);
        myDB.open();

        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAdress = (TextView) findViewById(R.id.tvAdress);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String adress = i.getStringExtra("adress");
        String phone = i.getStringExtra("phone");
        String website = i.getStringExtra("website");

        tvName.setText(name);
        tvAdress.setText(adress);
        tvPhone.setText(phone);
        tvWebsite.setText(website);
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
