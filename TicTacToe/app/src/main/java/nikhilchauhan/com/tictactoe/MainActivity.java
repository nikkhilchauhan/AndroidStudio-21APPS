package nikhilchauhan.com.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0; //for turning players

    int[] gameState = {0,0,0,0,0,0,0,0,0}; //protecting from getting tapped again

    //TextView myText = (TextView) findViewById(R.id.lbl);

    public void imageTapped(View view) {
        ImageView tapped = (ImageView) view;

        int tappedTag = Integer.parseInt(tapped.getTag().toString()); //storing positions 0-8

        if (gameState[tappedTag] == 0) {

            gameState[tappedTag]=1;

            if (activePlayer == 0) {
                tapped.setImageResource(R.drawable.cross);
                activePlayer = 1;
                //tapped.animate().rotation(360).setDuration(1000);
                // Log.i("xyz","tapped"+tapped.getTag().toString());

                  if(((gameState[0] == 1) && (gameState[1] == 1) && (gameState[2] == 1)) || ((gameState[3] == 1) && (gameState[4] == 1) && (gameState[5] == 1)) || ((gameState[6] == 1) && (gameState[7] == 1) && (gameState[8] == 1))
                          || ((gameState[0] == 1) && (gameState[3] == 1) && (gameState[6] == 1)) || ((gameState[1] == 1) && (gameState[4] == 1) && (gameState[7] == 1)) || ((gameState[2] == 1) && (gameState[5] == 1) && (gameState[8] == 1))
                          || ((gameState[0] == 1) && (gameState[4] == 1) && (gameState[8] == 1)) || ((gameState[2] == 1) && (gameState[4] == 1) && (gameState[6] == 1))){

                      for(int i=0;i<=8;i++){
                          gameState[i]=1;
                      }

                      //myText.setText("Cross Won");
                       Log.i("winner","Cross Won");

                  }


            } else {

                tapped.setImageResource(R.drawable.nought);
                //tapped.animate().rotation(360).setDuration(1000);
                activePlayer = 0;

                if(((gameState[0] == 1) && (gameState[1] == 1) && (gameState[2] == 1)) || ((gameState[3] == 1) && (gameState[4] == 1) && (gameState[5] == 1)) || ((gameState[6] == 1) && (gameState[7] == 1) && (gameState[8] == 1))
                        || ((gameState[0] == 1) && (gameState[3] == 1) && (gameState[6] == 1)) || ((gameState[1] == 1) && (gameState[4] == 1) && (gameState[7] == 1)) || ((gameState[2] == 1) && (gameState[5] == 1) && (gameState[8] == 1))
                        || ((gameState[0] == 1) && (gameState[4] == 1) && (gameState[8] == 1)) || ((gameState[2] == 1) && (gameState[4] == 1) && (gameState[6] == 1))){

                    for(int i=0;i<=8;i++){
                        gameState[i]=1;
                    }
                    //myText.setText("Nought Won");
                    Log.i("winner","Nought Won");
                }
            }

        }
    }

    public void btnReset(View view){
        activePlayer = 0;

        for(int i=0;i<=8;i++){

           gameState[i]=0;


       }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}
