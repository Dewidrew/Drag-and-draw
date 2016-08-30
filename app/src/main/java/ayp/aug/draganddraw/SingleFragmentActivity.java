package ayp.aug.draganddraw;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends AppCompatActivity {

       public static final String TAG = "SingleFragment";
           @LayoutRes
           protected int getLayoutResId() {
               return R.layout.activity_single_fragment;
           }
           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(getLayoutResId());


               //Create Fragment Container
               FragmentManager fm = getSupportFragmentManager();
               Fragment f = fm.findFragmentById(R.id.alone_fragment);


               if (f == null) {
                   f = onCreateFragment(); // Create Fragment

                   // Set Fragment to Fragment Container
                   fm.beginTransaction()
                           .add(R.id.alone_fragment, f)
                           .commit();

               }

           }

           protected abstract Fragment onCreateFragment();
}
