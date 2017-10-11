package unitedapps.com.googleandroidcourses.Courses.DevelopingAndroidApps.Sunshine_L9;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import unitedapps.com.googleandroidcourses.R;

/**
   Created by Dasser on 13-Jul-17.
 */

public class ss9_WindView extends View {
    private Paint mDirectionsCirclePaint, mMainCirclePaint, mCircleTextPaint, mCircleLinePaint, mCircleLineTextPaint;
    Path D0Path, D45Path, D90Path, D135Path, D180Path, D225Path, D270Path, D315Path;
    RectF rect;
    //private float center.x;
    //private float center.y;
    private float mRadius;
    float rotation = 0;
    private float radius;
    public static final double ss9_MIN_VALUE = 0;
    public static final double ss9_MAX_VALUE = 100;
    private PointF center = new PointF();
    private RectF circleRect = new RectF();
    private Path segment = new Path();
    private Paint fillPaint = new Paint();
    private double value;

    public ss9_WindView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleFillView,
                0, 0);

        try
        {
            value = a.getInteger(R.styleable.CircleFillView_value, 0);
            adjustValue(value);
        }
        finally
        {
            a.recycle();
        }

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //int myHeight = hSpecSize;

        //int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        //int myWidth = wSpecSize;

        /*if(hSpecMode == MeasureSpec.EXACTLY){
            myHeight = hSpecSize;
        }else if(hSpecMode == MeasureSpec.AT_MOST){
            //wrap_content
        }

        if(wSpecMode == MeasureSpec.EXACTLY){
            myWidth = wSpecSize;
        }else if(wSpecMode == MeasureSpec.AT_MOST){
            //wrap_content
        }*/

        int size = Math.min(wSpecSize, hSpecSize);
        setMeasuredDimension(size, size);
        //setMeasuredDimension(myWidth, myHeight);
    }

    private void init() {
        mDirectionsCirclePaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mDirectionsCirclePaint.setStyle(Paint.Style.STROKE);
        mDirectionsCirclePaint.setColor(Color.GRAY);
        mDirectionsCirclePaint.setStrokeWidth(25);

        mCircleTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleTextPaint.setStyle(Paint.Style.FILL);
        mCircleTextPaint.setColor(Color.BLACK);
        mCircleTextPaint.setTextSize(46);

        mMainCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMainCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMainCirclePaint.setColor(Color.BLUE);

        mCircleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleLinePaint.setStyle(Paint.Style.FILL);
        mCircleLinePaint.setColor(Color.BLACK);
        mCircleLinePaint.setStrokeWidth(5);

        mCircleLineTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleLineTextPaint.setStyle(Paint.Style.FILL);
        mCircleLineTextPaint.setColor(Color.BLACK);
        mCircleLineTextPaint.setTextSize(36);

        fillPaint.setColor(Color.RED);

        D0Path = new Path();
        D45Path = new Path();
        D90Path = new Path();
        D135Path = new Path();
        D180Path = new Path();
        D225Path = new Path();
        D270Path = new Path();
        D315Path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        D0Path.addArc(rect, 270, 180);
        D45Path.addArc(rect, 315, 180);
        D90Path.addArc(rect, 0, 180);
        D135Path.addArc(rect, 45, 180);
        D180Path.addArc(rect, 90, 180);
        D225Path.addArc(rect, 135, 180);
        D270Path.addArc(rect, 180, 180);
        D315Path.addArc(rect, 225, 180);

        canvas.drawCircle(center.x, center.y, mRadius, mDirectionsCirclePaint);
        canvas.drawCircle(center.x, center.y, mRadius, mMainCirclePaint);

        canvas.save();
        canvas.rotate(rotation, center.x, center.y);
        canvas.drawTextOnPath("N", D0Path, -15f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("N E", D45Path, -30f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("E", D90Path, -15f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("S E", D135Path, -30f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("S", D180Path, -15f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("S W", D225Path, -30f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("W", D270Path, -15f, 0.0f, mCircleTextPaint);
        canvas.drawTextOnPath("N W", D315Path, -30f, 0.0f, mCircleTextPaint);
        canvas.restore();

        canvas.drawPath(segment, fillPaint);

        canvas.drawLine((float) (center.x/1.5), (float) (center.y*0.4), (float) (center.x*1.3), (float) (center.y*0.4), mCircleLinePaint);
        canvas.drawLine((float) (center.x/1.5), (float) (center.y*0.7), (float) (center.x*1.3), (float) (center.y*0.7), mCircleLinePaint);
        canvas.drawLine((float) (center.x/1.5), center.y, (float) (center.x*1.3),  center.y, mCircleLinePaint);
        canvas.drawLine((float) (center.x/1.5), (float) (center.y*1.3), (float) (center.x*1.3), (float) (center.y*1.3), mCircleLinePaint);
        canvas.drawLine((float) (center.x/1.5), (float) (center.y*1.6), (float) (center.x*1.3), (float) (center.y*1.6), mCircleLinePaint);

        canvas.drawText("40", (float) (center.x*0.94), (float) (center.y*0.37), mCircleLineTextPaint);
        canvas.drawText("20", (float) (center.x*0.94), (float) (center.y*0.67), mCircleLineTextPaint);
        canvas.drawText("0", (float) (center.x*0.96), (float) (center.y* 0.97), mCircleLineTextPaint);
        canvas.drawText("-20", (float) (center.x*0.92), (float) (center.y*1.27), mCircleLineTextPaint);
        canvas.drawText("-40", (float) (center.x*0.92), (float) (center.y*1.57), mCircleLineTextPaint);


    }

    public void setWind(float speed, float degree)
    {
        int v;
        if(speed < 0){
            v = 0;
            speed = Math.abs(speed) /20;
            speed = (3 - speed) * 50/3;
        }else if (speed == 0){
            v = 50;
            speed = 0;
        }else {
            v = 50;
            speed = speed / 20 * 50/3;
        }
        Log.i("ZZ_1", speed + ",   " + v );
        setValue(v + speed);

        rotation = degree;
        invalidate();
        init();
    }




    public void setValue(double value)
    {
        adjustValue(value);
        setPaths();

        invalidate();
        init();
    }

    public double getValue()
    {
        return value;
    }

    private void adjustValue(double value)
    {
        this.value = Math.min(ss9_MAX_VALUE, Math.max(ss9_MIN_VALUE, value));
    }

    private void setPaths()
    {
        float y = (float) (center.y + radius - (2 * radius * value / 100 - 1));
        float x = center.x - (float) Math.sqrt(Math.pow(radius, 2) - Math.pow(y - center.y, 2));

        float angle = (float) Math.toDegrees(Math.atan((center.y - y) / (x - center.x)));
        float startAngle = 180 - angle;
        float sweepAngle = 2 * angle - 180;

        segment.rewind();
        segment.addArc(circleRect, startAngle, sweepAngle);
        segment.close();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        center.x = getWidth() / 2;
        center.y = getHeight() / 2;

        mRadius = (float) (Math.min(w, h) / 2f - (w*0.05));
        //radius = (float) (Math.min(getWidth(), getHeight()) / 2 - (w*0.05));
        radius = (float) (Math.min(w, h) / 2f - (w*0.05));

        rect = new RectF(center.x - mRadius, center.y - mRadius, center.x + mRadius, center.y + mRadius);
        circleRect.set(center.x - radius, center.y - radius, center.x + radius, center.y + radius);

        setPaths();
    }

}
