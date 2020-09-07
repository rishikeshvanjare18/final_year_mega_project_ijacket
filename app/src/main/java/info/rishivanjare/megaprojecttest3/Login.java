package info.rishivanjare.megaprojecttest3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.VISIBLE;

public class Login extends AppCompatActivity {

    String email,password;
    private EditText edusrname, edpass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar pBar;
    public static String jacketid;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("ijacket 1.0");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            jacketid = user.getEmail();
            //Toast.makeText(this,email, Toast.LENGTH_SHORT).show();
        }


        edusrname=findViewById(R.id.username);
        edpass=findViewById(R.id.password);

        pBar = findViewById(R.id.proglogin);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(Login.this,Home.class));
                }
                else
                {
                    Toast.makeText(Login.this, "Login Problem",Toast.LENGTH_LONG).show();
                }
            }

        };


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
      //  updateUI(currentUser);
    }

    /*   public void onStart(){
           super.onStart();
           mAuth.addAuthStateListener(mAuthListener);
       }
   */
    public void signin(View view)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            startlogin();
        }
        else
        {
            Toast.makeText(this,"Internet Connection unavailable!",Toast.LENGTH_LONG).show();
        }


    }

    public void startlogin()
    {
        email=edusrname.getText().toString();
        password= edpass.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Fields are empty!", Toast.LENGTH_LONG).show();

        }
        else
        {
            pBar.setVisibility(VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent= new Intent(Login.this,Home.class);
                        startActivity(intent);
                        pBar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Login Successful..", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Login Failed!!", Toast.LENGTH_LONG).show();
                        pBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
                System.exit(0);

            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}
