package nikhilchauhan.com.whoo;

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

    }


    @Override
    public void onStart(){
        super.onStart();

        //If User is already logged-in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent loginIntent= new Intent(this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }


    }

}

