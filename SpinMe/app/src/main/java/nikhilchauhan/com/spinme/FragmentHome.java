package nikhilchauhan.com.spinme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class FragmentHome extends Fragment {
    View view;

    Button button;
    TextView textView;
    ImageView imageView;
    Random r;
    int degree = 0, degree_old = 0;

    //becuase there 8 sector on the wheel(45 degree each)
    private static final float Factor = 22.5f;

    public FragmentHome() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        button = (Button) view.findViewById(R.id.btnSpin);
        textView = (TextView) view.findViewById(R.id.textView);
        imageView = (ImageView) view.findViewById(R.id.imgSpin);
        r = new Random();

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
                        button.setEnabled(true);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageView.startAnimation(myRotate);
                button.setEnabled(false);

            }
        });

        return view;
    }

    private String currentNumber(int degrees){
        String text = "";

        if (degrees>=(Factor*1) && degrees < (Factor * 3)){
            text =  "Click";
        }

        if (degrees>=(Factor*3) && degrees < (Factor * 5)){
            text =  "Bonus";
        }

        if (degrees>=(Factor*5) && degrees < (Factor * 7)){
            text =  "Bonus";
        }

        if (degrees>=(Factor*7) && degrees < (Factor * 9)){
            text =  "Bonus";
        }

        if (degrees>=(Factor*9) && degrees < (Factor * 11)){
            text =  "Click";
        }

        if (degrees>=(Factor*11) && degrees < (Factor * 13)){
            text =  "Bonus";
        }

        if (degrees>=(Factor*13) && degrees < (Factor * 15)){
            text =  "Bonus";
        }

        if ((degrees >= (Factor*15) && degrees < 360) || (degrees>=0 && degrees <(Factor*1))){
            text="Bonus";
        }
        return text;
    }

}


