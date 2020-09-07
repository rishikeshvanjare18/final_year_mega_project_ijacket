package info.rishivanjare.megaprojecttest3;

import androidx.fragment.app.FragmentActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapMentor extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_mentor);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    @Override
    public void onMapReady(GoogleMap googleMap) {


        final double[] longitude = new double[1];
        final double[] latitude = new double[1];

        mMap = googleMap;
     //   final MarkerOptions mop = new MarkerOptions();

        String username= Login.jacketid;
        String substring = username.substring(0, username.length() - 4);
        //Toast.makeText(this,substring,Toast.LENGTH_LONG).show();
        String path1= substring +"/location/lang";
        String path2= substring +"/location/lat";
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Toast.makeText(this,path1+" "+path2,Toast.LENGTH_LONG).show();
        DatabaseReference lat,lng;
        lng = database.getReference(path1);
        lat = database.getReference(path2);
        //lat.setValue("16.69789");
        //lng.setValue("74.19722");

        //get latitute from firebase

        lat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                latitude[0] =Double.parseDouble(dataSnapshot.getValue().toString());
                //Toast.makeText(MapMentor.this,""+latitude[0],Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MapMentor.this,"Failed to read lat value.",Toast.LENGTH_LONG).show();

            }
        });


        // Read langitude from  the firebase

        lng.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mMap.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                longitude[0] = Double.parseDouble(value);
                //Toast.makeText(MapMentor.this,""+longitude[0],Toast.LENGTH_LONG).show();
                //System.out.println(langitude);

                LatLng jacket = new LatLng(latitude[0], longitude[0]);
                mMap.addMarker(new MarkerOptions().position(jacket).title(Login.jacketid));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jacket));
                // Toast.makeText(MapMentor.this,""+ latitude[0] +"abc"+ longitude[0],Toast.LENGTH_LONG).show();

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18), 2000, null);
               // mop.position(jacket);
               // mop.title("User");
               // mop.icon(icon);
              //  mMap.addMarker(mop);

              //  mMap.moveCamera(CameraUpdateFactory.newLatLng(jacket));
              //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jacket,13f));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MapMentor.this,"Failed to read value.",Toast.LENGTH_LONG).show();
            }



        });


/*

        LatLng jacket = new LatLng(latitude[0], longitude[0]);
        mMap.addMarker(new MarkerOptions().position(jacket).title("jacket user"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jacket));
       // Toast.makeText(MapMentor.this,""+ latitude[0] +"abc"+ longitude[0],Toast.LENGTH_LONG).show();

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

        MarkerOptions mop = new MarkerOptions();
        mop.position(jacket);
        //mop.title("User");
        //mop.icon(icon);
        mMap.addMarker(mop);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(jacket));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(jacket,13f));
*/
    }


    public void refresh(View view)
    {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
    }

}


