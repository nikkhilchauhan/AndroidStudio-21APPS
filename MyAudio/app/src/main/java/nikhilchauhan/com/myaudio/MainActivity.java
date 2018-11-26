package nikhilchauhan.com.myaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer myMediaPlayer;
    SeekBar timeLine;
    Button myBtn;

    int flag=0;

    public void btnPlay(View view) {

        if (flag == 0) {

            //For playing any audio randomly
            String[] myList = {"dilbar", "bintere", "kamariya", "paniyon"};
            Random ran = new Random();
            int randomNumber = ran.nextInt(4);
            int myMusic = getResources().getIdentifier(myList[randomNumber], "raw", "nikhilchauhan.com.myaudio");


            myMediaPlayer = MediaPlayer.create(this, myMusic);


            timeLine.setMax(myMediaPlayer.getDuration());
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    timeLine.setProgress(myMediaPlayer.getCurrentPosition());
                }
            }, 0, 1000);

            myMediaPlayer.start();
            myBtn.setText("Stop");
            flag = 1;

        } else {

            myMediaPlayer.stop();
            myBtn.setText("Start");
            flag=0;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtn=(Button) findViewById(R.id.button4);
        timeLine = (SeekBar) findViewById(R.id.seekBar);


        //WAS JUST TRYING OUT DIFFERENT THINGS(Ignore Codes Below this)

       //for playing random music
//        String[] myList = {"dilbar", "bintere", "kamariya" , "paniyon"};
//        Random ran=new Random();
//        int randomNumber=ran.nextInt(4);
//        int myMusic = getResources().getIdentifier(myList[randomNumber],"raw","nikhilchauhan.com.myaudio");
//
//
//        myMediaPlayer = MediaPlayer.create(this, myMusic);
//
//        final SeekBar timeLine= (SeekBar) findViewById(R.id.seekBar);
//        timeLine.setMax(myMediaPlayer.getDuration());
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                timeLine.setProgress(myMediaPlayer.getCurrentPosition());
//            }
//        },0,1000);

        //myMediaPlayer = MediaPlayer.create(this, R.raw.dilbar);

        //getting context from audio service
//        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//         int myMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//         int myCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//
       //setting context values to seekBar
//          SeekBar volumeBar= findViewById(R.id.seekBar);
//          volumeBar.setMax(myMaxVolume);
//          volumeBar.setProgress(myCurrentVolume);
//
         //set on change listner
//          volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//               audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


    }
}
