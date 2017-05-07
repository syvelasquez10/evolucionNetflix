// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import java.util.Iterator;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import android.view.SurfaceView;

public class TappableSurfaceView extends SurfaceView
{
    public static final int MODE_NORMAL = 0;
    public static final int MODE_SCALE = 3;
    public static final int MODE_STRETCH = 2;
    public static final int MODE_ZOOM = 1;
    private static final String TAG = "@@@";
    private final NavigationBarListener listener;
    private final List<TapListener> listeners;
    private int mPheight;
    private int mPreviousMeasureHeight;
    private int mPreviousMeasureWidth;
    private int mPwidth;
    private float mScale;
    private int mVideoHeight;
    private int mVideoWidth;
    private int mode;
    private SurfaceMeasureListener surfaceMeasureListener;
    
    public TappableSurfaceView(final Context context, final AttributeSet set) {
        super(context, set);
        this.listeners = new ArrayList<TapListener>();
        this.mPheight = 0;
        this.mPwidth = 0;
        this.mScale = 1.0f;
        this.mPreviousMeasureWidth = 0;
        this.mPreviousMeasureHeight = 0;
        this.mode = 0;
        this.listener = NavigationBarListener.getInstance(context, this);
        this.initSurface(context);
    }
    
    private void initSurface(final Context context) {
        Log.d("@@@", "INIT_SURFACE");
    }
    
    public void addTapListener(final TapListener tapListener) {
        if (tapListener != null) {
            this.listeners.add(tapListener);
        }
        if (this.listeners.size() > 0) {
            this.listener.startListening();
        }
    }
    
    public boolean canVideoBeZoomed() {
        if (Log.isLoggable("@@@", 3)) {
            Log.w("@@@", this.mVideoWidth + " X " + this.mVideoHeight + ", " + this.mPwidth + " X " + this.mPheight);
        }
        return this.mVideoWidth == 0 || this.mVideoHeight == 0 || this.mPwidth == 0 || this.mPheight == 0 || this.mVideoWidth * this.mPheight != this.mVideoHeight * this.mPwidth;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public float getScale() {
        return this.mScale;
    }
    
    public int getVideoHeight() {
        return this.mVideoHeight;
    }
    
    public int getVideoWidth() {
        return this.mVideoWidth;
    }
    
    protected void onMeasure(int n, int n2) {
        Log.i("@@@", "onMeasure");
        final ViewGroup viewGroup = (ViewGroup)this.getParent();
        if (AndroidUtils.getAndroidVersion() < 16) {
            n = getDefaultSize(this.mVideoWidth, n);
            n2 = getDefaultSize(this.mVideoHeight, n2);
        }
        else {
            int n3 = viewGroup.getMeasuredWidth();
            final int measuredHeight = viewGroup.getMeasuredHeight();
            int defaultSize;
            if (n3 == 0 || (defaultSize = measuredHeight) == 0) {
                n3 = getDefaultSize(this.mVideoWidth, n);
                defaultSize = getDefaultSize(this.mVideoHeight, n2);
                Log.d("@@@", "use default size");
            }
            int n4;
            if (n3 == 0 || (n4 = defaultSize) == 0) {
                n3 = 1920;
                n4 = 1080;
                Log.d("@@@", "use hardcode size");
            }
            n2 = n4;
            n = n3;
            if (Log.isLoggable("@@@", 3)) {
                Log.d("@@@", "parent size: " + n3 + " x " + n4);
                n2 = n4;
                n = n3;
            }
        }
        this.mPheight = n2;
        this.mPwidth = n;
        if (this.mPheight >= this.mPwidth && this.mPreviousMeasureWidth > 0 && this.mPreviousMeasureHeight > 0) {
            if (Log.isLoggable("@@@", 3)) {
                Log.d("@@@", "use previous measure size: " + this.mPreviousMeasureWidth + 'x' + this.mPreviousMeasureHeight);
            }
            this.setMeasuredDimension(this.mPreviousMeasureWidth, this.mPreviousMeasureHeight);
        }
        else {
            int n5 = n2;
            int n6 = n;
            if (this.mVideoWidth > 0) {
                n5 = n2;
                n6 = n;
                if (this.mVideoHeight > 0) {
                    if (this.mVideoWidth * n2 > this.mVideoHeight * n) {
                        Log.d("@@@", "image too tall, correcting");
                        n5 = this.mVideoHeight * n / this.mVideoWidth;
                        n6 = n;
                    }
                    else if (this.mVideoWidth * n2 < this.mVideoHeight * n) {
                        Log.d("@@@", "image too wide, correcting");
                        n6 = this.mVideoWidth * n2 / this.mVideoHeight;
                        n5 = n2;
                    }
                    else {
                        n5 = n2;
                        n6 = n;
                        if (Log.isLoggable("@@@", 3)) {
                            Log.d("@@@", "aspect ratio is correct: " + n + "/" + n2 + "=" + this.mVideoWidth + "/" + this.mVideoHeight);
                            n5 = n2;
                            n6 = n;
                        }
                    }
                }
            }
            Log.d("@@@", "aspect ratio corrected: " + n6 + "x" + n5);
            switch (this.mode) {
                default: {
                    Log.i("@@@", "Stretch to full screen ...");
                    n2 = this.mPwidth;
                    n = this.mPheight;
                    break;
                }
                case 0: {
                    Log.i("@@@", "Follow Aspect ration");
                    n = n5;
                    n2 = n6;
                    break;
                }
                case 3: {
                    Log.i("@@@", "Scale surface");
                    n = n5;
                    n2 = n6;
                    if (n6 == 0) {
                        break;
                    }
                    n = n5;
                    n2 = n6;
                    if (n5 == 0) {
                        break;
                    }
                    n = n5;
                    n2 = n6;
                    if (this.mScale > 0.0f) {
                        n2 = (int)(n6 * this.mScale);
                        n = (int)(n5 * this.mScale);
                        break;
                    }
                    break;
                }
                case 1: {
                    Log.i("@@@", "Zoomin ...");
                    n = n5;
                    n2 = n6;
                    if (n6 == 0) {
                        break;
                    }
                    n = n5;
                    n2 = n6;
                    if (n5 == 0) {
                        break;
                    }
                    final float n7 = this.mPwidth / n6;
                    final float n8 = this.mPheight / n5;
                    if (n7 >= n8) {
                        n2 = this.mPwidth;
                        n = (int)(n5 * n7);
                        break;
                    }
                    n = this.mPheight;
                    n2 = (int)(n6 * n8);
                    break;
                }
            }
            final int[] array = new int[2];
            this.getLocationOnScreen(array);
            if (Log.isLoggable("@@@", 3)) {
                Log.d("@@@", "setting size: " + n2 + 'x' + n + ", start coordinates: " + array[0] + ", " + array[1]);
            }
            this.setMeasuredDimension(n2, n);
            this.mPreviousMeasureWidth = n2;
            this.mPreviousMeasureHeight = n;
            if (this.surfaceMeasureListener != null) {
                this.surfaceMeasureListener.onSurfaceMeasureChange(n2, n);
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        Log.d("TAP", "onTouchEvent " + motionEvent);
        if (motionEvent.getAction() == 1) {
            this.processOnTouchEvent(motionEvent);
        }
        return true;
    }
    
    void processOnTouchEvent(final MotionEvent motionEvent) {
        final Iterator<TapListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onTap(motionEvent);
        }
    }
    
    public boolean removeTapListener(final TapListener tapListener) {
        boolean remove;
        if (tapListener == null) {
            remove = false;
        }
        else {
            remove = this.listeners.remove(tapListener);
            if (this.listeners.size() <= 0) {
                this.listener.stopListening();
                return remove;
            }
        }
        return remove;
    }
    
    public void setMode(final int mode) {
        if (mode >= 0 && mode <= 2) {
            this.mScale = 1.0f;
            this.mode = mode;
            this.requestLayout();
            return;
        }
        Log.w("@@@", "Invalid mode");
    }
    
    public void setScale(final float mScale) {
        if (mScale > 0.0f) {
            this.mode = 3;
            this.mScale = mScale;
            this.requestLayout();
            return;
        }
        Log.w("@@@", "Invalid mode");
    }
    
    public void setSurfaceMeasureListener(final SurfaceMeasureListener surfaceMeasureListener) {
        this.surfaceMeasureListener = surfaceMeasureListener;
    }
    
    public void setVideoHeight(final int n) {
        this.mVideoHeight = n;
        this.mPreviousMeasureHeight = n;
    }
    
    public void setVideoWidth(final int n) {
        this.mVideoWidth = n;
        this.mPreviousMeasureWidth = n;
    }
    
    public interface SurfaceMeasureListener
    {
        void onSurfaceMeasureChange(final int p0, final int p1);
    }
    
    public interface TapListener
    {
        void onTap(final MotionEvent p0);
    }
}
