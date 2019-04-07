package nikhilchauhan.com.firebaselogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText myEmail;
    EditText myPass;

    private FirebaseAuth mAuth;

    //check if empty


    @Override
    protected void onStart() {
        super.onStart();
       //if already logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        Toast.makeText(this,"Already Logged In",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEmail = (EditText) findViewById(R.id.email_id);
        myPass = (EditText) findViewById(R.id.password_id);
        //myButton = (Button) findViewById(R.id.btnLogin_id);


        //String email1 = myEmail.getText().toString();
        //String pass1 = myPass.getText().toString();

        mAuth = FirebaseAuth.getInstance();

    }

    public void btnLogin(View view){

        final String email1 = myEmail.getText().toString();
        final String pass1 = myPass.getText().toString();

        mAuth.createUserWithEmailAndPassword(email1, pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    //MainActivity.this,
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }


}
