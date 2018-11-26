package nikhilchauhan.com.patatap;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void btnPlay(View view){

        int myId = view.getId();
        //Log.i("Message1","id is" +myId);

        String nameId;
        nameId = view.getResources().getResourceEntryName(myId);

        Log.i("Message2","NameId is" +nameId);

        int myMusic = getResources().getIdentifier(nameId,"raw","nikhilchauhan.com.patatap");

        MediaPlayer myaudio = MediaPlayer.create(this,myMusic);
        myaudio.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
