package nikhilchauhan.com.myvideos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView MyVideoView = (VideoView) findViewById(R.id.videoView);

        MyVideoView.setVideoPath("android.resource://"+ getPackageName()+ "/" +R.raw.nikhil);  //pointing resource


        //adding controls
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(MyVideoView); //telling mediacon that myvidview is available
        MyVideoView.setMediaController(mediaController); //telling myvidview that mediacon is available

        //start
        MyVideoView.start();

    }
}
