package nikhilchauhan.com.dotme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        //FirebaseAuth.getInstance().signOut();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent loginIntent = new Intent(this, SubjectActivity.class);
            startActivity(loginIntent);
            finish();
        }

//        Intent intentSubject=new Intent(MainActivity.this,LoginActivity.class);
//        startActivity(intentSubject);

        }
    }


