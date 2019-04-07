package co.hotnot.nikhil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
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

public class AuthActivity extends AppCompatActivity {
    Button btnSend;
    Button btnVerify;
    PinView pinView;
    EditText txtPhone;
    String phonenumber;
    ProgressBar progressBar;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnSend= findViewById(R.id.btn_send);
        btnVerify= findViewById(R.id.btn_verify);
        pinView= findViewById(R.id.pinView);
        txtPhone= findViewById(R.id.txt_phone);
        progressBar= findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();

        // When SEND OTP btn is tapped
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                phonenumber = txtPhone.getText().toString();
                   if (phonenumber.isEmpty()) {
                       txtPhone.setError("Enter mobile number");
                       progressBar.setVisibility(View.INVISIBLE);
                   } else if (phonenumber.length()==10) {
                       btnSend.setEnabled(false);
                       phonenumber = "+91" + txtPhone.getText().toString();
                       progressBar.setVisibility(View.VISIBLE);
                       PhoneAuthProvider.getInstance().verifyPhoneNumber(
                               phonenumber,
                               60,
                               TimeUnit.SECONDS,
                               AuthActivity.this,
                               mCallbacks //declared below
                       );
                   }else {
                       progressBar.setVisibility(View.INVISIBLE);
                       txtPhone.setError("Invalid mobile number");
                       txtPhone.requestFocus();
                   }
               }
        });


        //When Verify Button is Tapped
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String verificationcode = pinView.getText().toString();
                if(verificationcode.isEmpty()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AuthActivity.this,"Enter verification code",Toast.LENGTH_SHORT).show();
                }else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationcode);
                    signInWithPhoneAuthCredential(credential);
                    btnVerify.setEnabled(false);
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
                Toast.makeText(AuthActivity.this,"No Internet Connection or Wrong Mobile Number",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                btnSend.setEnabled(true);
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

                //UI Update after otp is sent
                progressBar.setVisibility(View.INVISIBLE);
                txtPhone.setVisibility(View.INVISIBLE);
                btnSend.setVisibility(View.INVISIBLE);
                pinView.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.VISIBLE);
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
                            Intent setupIntnet = new Intent(AuthActivity.this, SetupActivity.class);
                            startActivity(setupIntnet);
                            finish();

                        } else {

                            //If OTP entered is wrong
                            progressBar.setVisibility(View.INVISIBLE);
                            btnVerify.setEnabled(true);
                            Toast.makeText(AuthActivity.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
