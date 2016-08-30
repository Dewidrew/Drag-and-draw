package ayp.aug.draganddraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Hattapong on 8/30/2016.
 */
public class DragAndDrawActivity extends SingleFragmentActivity {
    @Override
    protected Fragment onCreateFragment() {
        return DragAndDrawFragment.newInstance();
    }



}
