package ayp.aug.draganddraw;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by Hattapong on 8/30/2016.
 */
public class Box {
    PointF mStart;
    PointF mEnd;

    public PointF getStart() {
        return mStart;
    }

    public void setStart(PointF mStart) {
        this.mStart = mStart;
    }

    public PointF getEnd() {
        return mEnd;
    }

    public void setEnd(PointF mEnd) {
        this.mEnd = mEnd;
    }
}
