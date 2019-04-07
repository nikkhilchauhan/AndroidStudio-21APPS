package co.hotnot.nikhil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private  String currentUserName;
    //private Button btnLogout;
    private FloatingActionButton addPostBtn;

    private TextView mProfileLabel;
    private TextView mUsersLabel;
    private TextView mNotificationLabel;

    private ViewPager mMainPager;

    private PagerViewAdapter mPagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnLogout= findViewById(R.id.btnLogout);
        mAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        addPostBtn= findViewById(R.id.btn_addPost);

        //Logout btn is Tapped
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent authIntent= new Intent(MainActivity.this, AuthActivity.class);
//                startActivity(authIntent);
//                finish();
//            }
//        });



        //>>>>
        mProfileLabel = (TextView) findViewById(R.id.profileLabel);
        mUsersLabel = (TextView) findViewById(R.id.usersLabel);
        mNotificationLabel = (TextView) findViewById(R.id.notificationsLabel);

        mMainPager = (ViewPager) findViewById(R.id.mainPager);
        mMainPager.setOffscreenPageLimit(2);

        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(mPagerViewAdapter);

        //For setting HomeFragment as default
        mMainPager.setCurrentItem(1);
        changeTabs(1);

        mProfileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //addPostBtn.setVisibility(View.INVISIBLE);
                mMainPager.setCurrentItem(0);

            }
        });

        mUsersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMainPager.setCurrentItem(1);
                //addPostBtn.setVisibility(View.VISIBLE);

            }
        });

        mNotificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //addPostBtn.setVisibility(View.INVISIBLE);
                mMainPager.setCurrentItem(2);

            }
        });

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                changeTabs(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //>>>>>

        //Add new post btn is Tapped
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent IntentNewPost=new Intent(MainActivity.this,NewPostActivity.class);
                startActivity(IntentNewPost);

            }
        });



    }

    //--------------------------------------------------------------------->>>>>>

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUSer=FirebaseAuth.getInstance().getCurrentUser();
        if (currentUSer==null){
            //If User is already logged-in
            sendUserToAuth();
        }else{
          currentUserName=mAuth.getCurrentUser().getUid();
          firebaseFirestore.collection("Users").document(currentUserName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                  if (task.isSuccessful()){
                      if (!task.getResult().exists()){
                          Intent setupIntent=new Intent(MainActivity.this,SetupActivity.class);
                          startActivity(setupIntent);
                          finish();
                      }
                  }else {
                      String errorMessage= task.getException().getMessage();
                      Toast.makeText(MainActivity.this, "Error:"+errorMessage, Toast.LENGTH_SHORT).show();
                  }
              }
          });
        }

    }

    private void sendUserToAuth(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent authIntent= new Intent(MainActivity.this,AuthActivity.class);
            startActivity(authIntent);
            finish();
        }
    }

    //>>
    private void changeTabs(int position) {

        if(position == 0){

            mProfileLabel.setTextColor(getColor(R.color.textTabBright));
            mProfileLabel.setTextSize(22);

            mUsersLabel.setTextColor(getColor(R.color.textTabLight));
            mUsersLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);

        }

        if(position == 1){

            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mUsersLabel.setTextColor(getColor(R.color.textTabBright));
            mUsersLabel.setTextSize(22);

            mNotificationLabel.setTextColor(getColor(R.color.textTabLight));
            mNotificationLabel.setTextSize(16);

        }

        if(position == 2){

            mProfileLabel.setTextColor(getColor(R.color.textTabLight));
            mProfileLabel.setTextSize(16);

            mUsersLabel.setTextColor(getColor(R.color.textTabLight));
            mUsersLabel.setTextSize(16);

            mNotificationLabel.setTextColor(getColor(R.color.textTabBright));
            mNotificationLabel.setTextSize(22);

        }

    }

    //>>

    //--------------------------------------------------------------------->>>>>>

}
