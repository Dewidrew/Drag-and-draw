package ayp.aug.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by Hattapong on 8/30/2016.
 */
public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxList = new ArrayList<>();
    private Paint mBackgroundPoint;
    private Paint mBoxPaint;

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        mBackgroundPoint = new Paint();
        mBackgroundPoint.setColor(0xfff8efe0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawPaint(mBackgroundPoint);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        mBoxPaint.setColor(color);

        for (Box box : mBoxList){
            float left = Math.min(box.getStart().x,box.getEnd().x);
            float top = Math.min(box.getStart().y,box.getEnd().y);
            float right = Math.max(box.getStart().x,box.getEnd().x);
            float bottom = Math.max(box.getStart().y,box.getEnd().y);

            canvas.drawRect(left,top,right,bottom,mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF currentPoint = new PointF(event.getX(),event.getY());
        String action = "NO";

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                action = "ACTION MOVE";
                if(mCurrentBox != null){
                    mCurrentBox.setEnd(currentPoint);
                    invalidate();
//                    logBox(mCurrentBox);
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_DOWN:
                action = "ACTION DOWN";
                mCurrentBox = new Box();
                mCurrentBox.setStart(currentPoint);
                mBoxList.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION CANCEL";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG,"Action="+action+", at x="+currentPoint.x+" y="+currentPoint.y);


        return true;
    }

    private void logBox(Box box){
        if(box != null){
            Log.d(TAG,"CurrentBox: "+box.toString()
                    + ", Start="+box.getStart()
                    + " , End="+box.getEnd());
        }

    }
}
