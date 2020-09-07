package info.rishivanjare.megaprojecttest3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 2000);

    }

    public void next(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, Login.class);
        MainActivity.this.startActivity(myIntent);
    }



    public void onBackPressed() {
        finish();
    }
}
