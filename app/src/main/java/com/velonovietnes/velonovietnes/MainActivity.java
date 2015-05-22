package com.velonovietnes.velonovietnes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity /*implements View.OnClickListener*/ {

    TextView tvMap;
    ImageView ivMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMap = (TextView) findViewById(R.id.tvMap);
        ivMap = (ImageView) findViewById(R.id.ivMap);
        /*tvMap.setOnClickListener(this);
        ivMap.setOnClickListener(this);*/
    }

   /*@Override
    public void onClick(View v) {                                                                   //Menu buttons
        switch (v.getId())
        {
            case R.id.tvMap:
                Intent maptvIntent = new Intent(MainActivity.this, MapActivity.class); // Jauztaisa map activity
                startActivity(mapIntent);
                break;
            case R.id.ivMap:
                Intent mapivIntent = new Intent(MainActivity.this, MapActivity.class); // Jauztaisa map activity
                startActivity(mapIntent);
        }
    }*/

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
