package co.hotnot.nikhil;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    //private Button btnLogout;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       //View view = inflater.inflate(R.layout.fragment_home, container, false);
       mAuth= FirebaseAuth.getInstance();
       //FirebaseAuth.getInstance().signOut();

        //btnLogout = (Button) view.findViewById(R.id.logout_btn);

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent authIntent= new Intent(getActivity(), AuthActivity.class);
//                startActivity(authIntent);
//            }
//        });

        // Inflate the layout for this fragment
        //return view;
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
