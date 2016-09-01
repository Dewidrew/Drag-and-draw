package ayp.aug.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hattapong on 8/30/2016.
 */
public class PointFingerView extends View {

    private static final String TAG = "PointFingerView";
    private Paint mBoxPaint;
    private Paint mBackgroundPoint;
    private List<PointF> mCircleList = new ArrayList<>();

    public PointFingerView(Context context) {
        super(context);
    }

    public PointFingerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPoint = new Paint();
        mBackgroundPoint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackgroundPoint);
        for (PointF p : mCircleList){
            canvas.drawCircle(p.x,p.y,150,mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = MotionEventCompat.getActionIndex(event);
        int action = MotionEventCompat.getActionMasked(event);
        int touchCount = event.getPointerCount();

        PointF currentPoint = new PointF(event.getX(), event.getY());

//        Log.i(TAG,"index="+index+",action="+actionToString(action)+",pointer="+currentPoint);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCircleList.add((new PointF(event.getX(),event.getY())));
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i = 0;i<touchCount;i++){
                    mCircleList.get(i).x = event.getX(i);
                    mCircleList.get(i).y = event.getY(i);
                }

                Log.i(TAG,"Move: " + currentPoint);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG,"Pointer down: " + currentPoint);
                mCircleList.add((new PointF(event.getX(index),event.getY(index))));
                break;


            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG,"Pointer up: "+currentPoint);
                mCircleList.remove(index);
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mCircleList.clear();
                break;

        }
        invalidate();
        return true;
    }
    public static String actionToString(int action) {
        switch (action) {

            case MotionEvent.ACTION_DOWN: return "Down";
            case MotionEvent.ACTION_MOVE: return "Move";
            case MotionEvent.ACTION_POINTER_DOWN: return "Pointer Down";
            case MotionEvent.ACTION_UP: return "Up";
            case MotionEvent.ACTION_POINTER_UP: return "Pointer Up";
            case MotionEvent.ACTION_OUTSIDE: return "Outside";
            case MotionEvent.ACTION_CANCEL: return "Cancel";
        }
        return "";
    }
}
