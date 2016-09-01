package ayp.aug.draganddraw;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

/**
 * Created by Hattapong on 9/1/2016.
 */
public class SunsetFragment extends Fragment {
    public static SunsetFragment newInstance() {

        Bundle args = new Bundle();

        SunsetFragment fragment = new SunsetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sunset,container,false);
        mSceneView = v;
        mSunView = v.findViewById(R.id.sun);
        mSkyView = v.findViewById(R.id.sky);

        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        return v;
    }

    private void startAnimation(){
        float sunYStart = mSunView.getTop();
        float sunHeight = mSunView.getHeight();
        float sunHalfHeight = sunHeight / 2;
        float skyEnd = mSkyView.getHeight();

        float sunHalfYEnd = skyEnd - sunHalfHeight;
        float sunYEnd = skyEnd;

        // 1.
        ObjectAnimator heightAnimator = ObjectAnimator
                .ofFloat(mSunView,"y",sunYStart,sunYEnd)
                .setDuration(3000);

        heightAnimator.setInterpolator(new AccelerateInterpolator());

        int colorBlueSky = getResources().getColor(R.color.blue_sky);
        int colorSunsetSky = getResources().getColor(R.color.sunset_sky);
        int colorNightSky = getResources().getColor(R.color.night_sky);

        // 2.
        ObjectAnimator sunsetAnimator = ObjectAnimator.ofInt(mSkyView,"backgroundColor",colorBlueSky,colorSunsetSky).setDuration(1500);
       // colorAnimator.setInterpolator(new AccelerateInterpolator());
        sunsetAnimator.setEvaluator(new ArgbEvaluator());

        // 3.
        ObjectAnimator nightAnimator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            nightAnimator = ObjectAnimator.ofArgb(mSkyView,
                    "backgroundColor",colorSunsetSky,colorNightSky)
                    .setDuration(1500);
        }else{
            nightAnimator = ObjectAnimator.ofInt(mSkyView,
                    "backgroundColor",colorSunsetSky,colorNightSky)
                    .setDuration(1500);
            nightAnimator.setEvaluator(new ArgbEvaluator());
        }

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mSunView,"scaleX",1f,1.5f).setDuration(3000);
        // colorAnimator.setInterpolator(new AccelerateInterpolator());
        sunsetAnimator.setEvaluator(new ArgbEvaluator());
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mSunView,"scaleY",1f,1.5f).setDuration(3000);
        // colorAnimator.setInterpolator(new AccelerateInterpolator());
        sunsetAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(heightAnimator)
                .with(sunsetAnimator)
                .with(scaleXAnimator)
                .with(scaleYAnimator)
                .before(nightAnimator);
        animatorSet.start();

    }
}
