package info.rishivanjare.megaprojecttest3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class firebasetest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebasetest);


        double log=20.2222,lat=20.2222;

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference lati = database.getReference("username/location/longitute:");
       // lati.setValue(Double.toString(log));
        DatabaseReference longi = database.getReference("username/location/latitute:");
       // longi.setValue(Double.toString(lat));

        // Read from the database
        lati.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                Toast.makeText(firebasetest.this,"latitute: "+ value ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(firebasetest.this,"Failed to read value.",Toast.LENGTH_LONG).show();

            }
        });

        // Read from the database
        longi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                Toast.makeText(firebasetest.this,"longitute: "+ value ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(firebasetest.this,"Failed to read value.",Toast.LENGTH_LONG).show();
            }
        });

    }
}
