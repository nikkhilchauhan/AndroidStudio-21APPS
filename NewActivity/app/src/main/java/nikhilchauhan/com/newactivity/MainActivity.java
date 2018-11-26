package nikhilchauhan.com.newactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[] colorArray = {"Red","Blue","Yellow"};

        //final ArrayList colorArrayList = new ArrayList(Arrays.asList(colorArray));

        final  ArrayList<String> colorArrayList=new ArrayList<String>();
        colorArrayList.add("White");
        colorArrayList.add("Red");
        colorArrayList.add("Pink");

        final ListView myListView = (ListView) findViewById(R.id.ListViewId);

        ArrayAdapter myArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,colorArrayList);

        myListView.setAdapter(myArrayAdapter);


        //moving to new activity
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(getApplicationContext(), secondActivity.class);

                //passing information to next activity
                myIntent.putExtra("nameKey",colorArrayList.get(position));

                //starting new activity
                startActivity(myIntent);
            }
        });




    }
}
