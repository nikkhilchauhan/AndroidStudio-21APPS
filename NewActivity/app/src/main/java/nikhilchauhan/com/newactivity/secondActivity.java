package nikhilchauhan.com.newactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView=(TextView) findViewById(R.id.textView);

        //geting values from firstActivity
        Intent myNewIntent = getIntent();
        String x=myNewIntent.getStringExtra("nameKey");
        textView.setText(x);

        //Toast.makeText(this, myNewIntent.getStringExtra("nameKey"), Toast.LENGTH_SHORT).show();

    }
}
