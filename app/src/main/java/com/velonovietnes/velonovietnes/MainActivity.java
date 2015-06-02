package com.velonovietnes.velonovietnes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView tvMap;
    ImageView ivMap;
    TextView tvService;
    ImageView ivService;
    TextView tvAvailable;
    ImageView ivAvailable;

    //Finding and assigning the views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMap = (TextView) findViewById(R.id.tvMap);
        ivMap = (ImageView) findViewById(R.id.ivMap);
        tvService = (TextView) findViewById(R.id.tvService);
        ivService = (ImageView) findViewById(R.id.ivService);
        tvAvailable = (TextView) findViewById(R.id.tvAvailable);
        ivAvailable = (ImageView) findViewById(R.id.ivAvailable);
        tvMap.setOnClickListener(this);
        ivMap.setOnClickListener(this);
        tvService.setOnClickListener(this);
        ivService.setOnClickListener(this);
        tvAvailable.setOnClickListener(this);
        ivAvailable.setOnClickListener(this);
    }

    //An on click even with a switch structure
   @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvMap:
                Intent maptvIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(maptvIntent);
                break;
            case R.id.ivMap:
                Intent mapivIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapivIntent);
                break;
            case R.id.tvService:
                Intent servicetvIntent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(servicetvIntent);
                break;
            case R.id.ivService:
                Intent serviceivIntent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(serviceivIntent);
                break;
            case R.id.tvAvailable:
                Intent availabletvIntent = new Intent(MainActivity.this, AvailableServicesActivity.class);
                startActivity(availabletvIntent);
                break;
            case R.id.ivAvailable:
                Intent availableivIntent = new Intent(MainActivity.this, AvailableServicesActivity.class);
                startActivity(availableivIntent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
