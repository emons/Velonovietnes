package com.velonovietnes.velonovietnes;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

public class MapsActivity extends FragmentActivity {

    private static final String NOM_BDD = "marqueur.db";

    private static final String TABLE_GEOPOINT = "geopoint";
    private static final String COL_ID = "ID";
    private static final String COL_LONG = "LONGITUDE";
    private static final String COL_LAT = "LATITUDE";

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleMap googleMap;
    private LatLng myPosition;
    private final LatLng LOCATION_RIGA = new LatLng(56.950678, 24.116441);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        // Getting reference to the SupportMapFragment of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        googleMap = fm.getMap();

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        /*if(location!=null){
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location

            myPosition = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));}*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_RIGA, 4));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.978998, 24.185752)).title("Brīvības gatve 312"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.982929, 24.202108)).title("T/C Alfa"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.979281, 24.184783)).title("Džutas iela 1"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.977968, 24.182889)).title("Veloveikals"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.949515, 24.083608)).title("TC Olympia").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951733,24.124743)).title("Pie Latvijas Investīciju un attīstības aģentūras ēkas").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.947467,24.106321)).title("Rātslaukums").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.954676, 24.125193)).title("Jaunais Rīgas teātris").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.990329, 24.219242)).title("Pie Bābelīša ezera glābšanas stacijas").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.97607, 24.176853)).title("Vidzemes priekšpilsētas izpilddirekcija").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.936277,24.07671)).title("Zemgales priekšpilsētas izpilddirekcija").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.954991,24.109572)).title("Parex banka").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.945355, 24.114829)).title("Rīgas Starptautiskā Autoosta").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.954184, 24.127736)).title("Rīgas domes Satiksmes departaments").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.970012, 24.102244)).title("Dinaz būve").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.955155, 24.10394)).title("LU Bioloģijas fakultāte").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.963228, 24.125708)).title("LU Ķīmijas fakultāte").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.959297, 24.109089)).title("LU Ģeogrāfijas un Zemes zinātņu fakultātes studentu pašpārvalde").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.935054, 24.077461)).title("Fizikas un matemātikas fakultātes studentu pašpārvalde").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.952634, 24.016467)).title("LU Pedagoģijas un psiholoģijas fakultāte").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.939672, 24.157584)).title("LU Sociālo zinātņu fakultāte").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951218, 24.060327)).title("LU Botāniskais dārzs").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.950691,24.116106)).title("LU Centrālā ēka").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.953441, 24.054941)).title("Rīgas Stradiņa universitāte").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.931899, 24.07215)).title("Rīgas Stradiņa universitātes kopmītnes").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.975234, 24.134721)).title("Biznesa augstskola").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.937869, 24.15621)).title("Biznesa Starptautiskā augstskola").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.947959, 24.12971)).title("Latvijas Universitātes Moderno valodu fakultātes").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.937015, 24.100614)).title("Pie laikraksta Diena redakcijas ēkas").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.944547, 24.140664)).title("Pie VIC Velokurjers").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951592, 24.109401)).title("Pie Vecrīgas caurlaižu punkta").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.947929, 24.114894)).title("Pie LU Ekonomikas fakultātes").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951891, 24.113992)).title("Alejā aiz Brīvības pieminekļa").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.96245, 24.110656)).title("Pie Rīgas domes Izglītības, jaunatnes un sporta departamenta").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.948602, 24.113413)).title("Galerija Centrs").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.948596, 24.113821)).title("Galerija centrs").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951955, 24.083855)).title("RTU velostāvvieta").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(56.951387,24.166306)).title("Deglava ielā 50 pie veikala Fans").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconsmall)));

    }
}
