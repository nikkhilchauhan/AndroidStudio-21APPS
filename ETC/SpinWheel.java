package nikhilchauhan.com.spinme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SpinActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageView;

    Random r;
    int degree = 0, degree_old=0;

    //becuase there 8 sector on the wheel(45 degree each)
    private  static final float Factor = 22.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin);

        button = (Button) findViewById(R.id.btnSpin);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imgSpin);
        r= new Random();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                degree_old = degree % 360;
                degree = r.nextInt(3600)+720;
                RotateAnimation myRotate = new RotateAnimation(degree_old, degree,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);
                myRotate.setDuration(3600);
                myRotate.setFillAfter(true);
                myRotate.setInterpolator(new DecelerateInterpolator());
                myRotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                       textView.setText("");

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        textView.setText(currentNumber(360-(degree % 360)));

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageView.startAnimation(myRotate);

            }
        });

    }

    private String currentNumber(int degrees){
    String text = "";

    if (degrees>=(Factor*1) && degrees < (Factor * 3)){
        text =  "BROWN";
    }

        if (degrees>=(Factor*3) && degrees < (Factor * 5)){
            text =  "PINK";
        }

        if (degrees>=(Factor*5) && degrees < (Factor * 7)){
            text =  "YELOOW";
        }

        if (degrees>=(Factor*7) && degrees < (Factor * 9)){
            text =  "GREEN";
        }

        if (degrees>=(Factor*9) && degrees < (Factor * 11)){
            text =  "BROWN";
        }

        if (degrees>=(Factor*11) && degrees < (Factor * 13)){
            text =  "PINK";
        }

        if (degrees>=(Factor*13) && degrees < (Factor * 15)){
            text =  "YELLOW";
        }

        if ((degrees >= (Factor*15) && degrees < 360) || (degrees>=0 && degrees <(Factor*1))){
            text="GREEN";
        }
    return text;

    }
}
