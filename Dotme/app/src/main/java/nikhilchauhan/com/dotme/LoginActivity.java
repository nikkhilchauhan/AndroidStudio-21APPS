package nikhilchauhan.com.dotme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private EditText txtMobile;
    private EditText txtOtp;
    private Button buttonOtp;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private ProgressBar progressBar;
    int i=0;

    String phonenumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtMobile = findViewById(R.id.txt_mobile);
        txtOtp = findViewById(R.id.txt_otp);
        buttonOtp = findViewById(R.id.btn_otp);
        relativeLayout1 = findViewById(R.id.relativeLayout1);
        relativeLayout2 = findViewById(R.id.relativeLayout2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        buttonOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i==0){
                    phonenumber=txtMobile.getText().toString();
                    if (phonenumber.isEmpty()) {
                        txtMobile.requestFocus();
                    }else if (phonenumber.length() < 10){
                        txtMobile.setError("Invalid mobile number");
                        txtMobile.requestFocus();
                    } else {
                        buttonOtp.setEnabled(false);
                        phonenumber = "+91"+txtMobile.getText().toString();
                        progressBar.setVisibility(View.VISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                phonenumber,
                                60,
                                TimeUnit.SECONDS,
                                LoginActivity.this,
                                mCallbacks //declared below
                        );
                    }
                }else{
                            //When Verify Button is Tapped
                            progressBar.setVisibility(View.VISIBLE);
                            String verificationcode = txtOtp.getText().toString();
                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationcode);
                            signInWithPhoneAuthCredential(credential);
                        }
                  }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential); //declared below
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this,"Failed to send OTP",Toast.LENGTH_SHORT).show();
                buttonOtp.setEnabled(true);
            }
            //After otp is sent to phone
            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                progressBar.setVisibility(View.INVISIBLE);
                relativeLayout2.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.GONE);
                buttonOtp.setText("Verify OTP");
                buttonOtp.setEnabled(true);
                i=1;
            }
        };
    }

    //Copied from firebase doc
    private void signInWithPhoneAuthCredential (PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //If Otp is entered is correct
                            FirebaseUser user = task.getResult().getUser();
                            Intent mainIntnet = new Intent(LoginActivity.this, SubjectActivity.class);
                            startActivity(mainIntnet);
                        } else {
                            //If OTP entered is wrong
                            progressBar.setVisibility(View.INVISIBLE);
                            txtOtp.setError("Invalid OTP");
                            txtOtp.requestFocus();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
