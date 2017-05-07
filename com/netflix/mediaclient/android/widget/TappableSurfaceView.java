// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import java.util.Iterator;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup;
import android.view.SurfaceHolder;
import com.netflix.mediaclient.media.MediaPlayerHelper;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.media.MediaPlayerHelperFactory;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import java.util.List;
import android.view.SurfaceView;

public class TappableSurfaceView extends SurfaceView
{
    public static final int MODE_NORMAL = 0;
    public static final int MODE_STRETCH = 2;
    public static final int MODE_ZOOM = 1;
    private static final String TAG = "@@@";
    private final NavigationBarListener listener;
    private final List<TapListener> listeners;
    private int mPheight;
    private int mPwidth;
    private int mVideoHeight;
    private int mVideoWidth;
    private int mode;
    private SurfaceMeasureListener surfaceMeasureListener;
    
    public TappableSurfaceView(final Context context, final AttributeSet set) {
        super(context, set);
        this.listeners = new ArrayList<TapListener>();
        this.mPheight = 0;
        this.mPwidth = 0;
        this.mode = 0;
        this.listener = NavigationBarListener.getInstance(context, this);
        this.initSurface(context);
    }
    
    private void initSurface(final Context context) {
        Log.d("@@@", "INIT_SURFACE");
        final PlayerType currentType = PlayerTypeFactory.getCurrentType(context);
        final MediaPlayerHelper instance = MediaPlayerHelperFactory.getInstance(currentType);
        int surfaceHolderType = 3;
        int surfaceHolderPixelFormat = -2;
        if (instance != null) {
            surfaceHolderType = instance.getSurfaceHolderType();
            surfaceHolderPixelFormat = instance.getSurfaceHolderPixelFormat();
        }
        else {
            Log.e("@@@", "Player or helper are not instantiated yet. This should NOT happen!");
        }
        final SurfaceHolder holder = this.getHolder();
        if (holder == null) {
            Log.e("@@@", "INIT_SURFACE Surface holder is NULL!");
            return;
        }
        if (PlayerTypeFactory.isSoftwarePlayer(currentType)) {
            Log.d("@@@", "INIT_SURFACE to " + surfaceHolderPixelFormat + " using " + instance);
            holder.setFormat(surfaceHolderPixelFormat);
        }
        holder.setType(surfaceHolderType);
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
    
    public int getVideoHeight() {
        return this.mVideoHeight;
    }
    
    public int getVideoWidth() {
        return this.mVideoWidth;
    }
    
    protected void onMeasure(int mPwidth, int mPheight) {
        Log.i("@@@", "onMeasure");
        final ViewGroup viewGroup = (ViewGroup)this.getParent();
        if (AndroidUtils.getAndroidVersion() < 16) {
            mPwidth = getDefaultSize(this.mVideoWidth, mPwidth);
            mPheight = getDefaultSize(this.mVideoHeight, mPheight);
        }
        else {
            final int measuredWidth = viewGroup.getMeasuredWidth();
            final int n = mPheight = viewGroup.getMeasuredHeight();
            mPwidth = measuredWidth;
            if (Log.isLoggable("@@@", 3)) {
                Log.d("@@@", "parent size: " + measuredWidth + " x " + n);
                mPheight = n;
                mPwidth = measuredWidth;
            }
        }
        this.mPheight = mPheight;
        this.mPwidth = mPwidth;
        int n2 = mPheight;
        int n3 = mPwidth;
        if (this.mVideoWidth > 0) {
            n2 = mPheight;
            n3 = mPwidth;
            if (this.mVideoHeight > 0) {
                if (this.mVideoWidth * mPheight > this.mVideoHeight * mPwidth) {
                    Log.d("@@@", "image too tall, correcting");
                    n2 = this.mVideoHeight * mPwidth / this.mVideoWidth;
                    n3 = mPwidth;
                }
                else if (this.mVideoWidth * mPheight < this.mVideoHeight * mPwidth) {
                    Log.d("@@@", "image too wide, correcting");
                    n3 = this.mVideoWidth * mPheight / this.mVideoHeight;
                    n2 = mPheight;
                }
                else {
                    n2 = mPheight;
                    n3 = mPwidth;
                    if (Log.isLoggable("@@@", 3)) {
                        Log.d("@@@", "aspect ratio is correct: " + mPwidth + "/" + mPheight + "=" + this.mVideoWidth + "/" + this.mVideoHeight);
                        n2 = mPheight;
                        n3 = mPwidth;
                    }
                }
            }
        }
        Log.d("@@@", "aspect ratio corrected: " + n3 + "x" + n2);
        switch (this.mode) {
            default: {
                Log.i("@@@", "Stretch to full screen ...");
                mPheight = this.mPwidth;
                mPwidth = this.mPheight;
                break;
            }
            case 0: {
                Log.i("@@@", "Follow Aspect ration");
                mPwidth = n2;
                mPheight = n3;
                break;
            }
            case 1: {
                Log.i("@@@", "Zoomin ...");
                mPwidth = n2;
                mPheight = n3;
                if (n3 == 0) {
                    break;
                }
                mPwidth = n2;
                mPheight = n3;
                if (n2 == 0) {
                    break;
                }
                final float n4 = this.mPwidth / n3;
                final float n5 = this.mPheight / n2;
                if (n4 >= n5) {
                    mPheight = this.mPwidth;
                    mPwidth = (int)(n2 * n4);
                    break;
                }
                mPwidth = this.mPheight;
                mPheight = (int)(n3 * n5);
                break;
            }
        }
        final int[] array = new int[2];
        this.getLocationOnScreen(array);
        if (Log.isLoggable("@@@", 3)) {
            Log.d("@@@", "setting size: " + mPheight + 'x' + mPwidth + ", start coordinates: " + array[0] + ", " + array[1]);
        }
        this.setMeasuredDimension(mPheight, mPwidth);
        if (this.surfaceMeasureListener != null) {
            this.surfaceMeasureListener.onSurfaceMeasureChange(mPheight, mPwidth);
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
            this.mode = mode;
            this.requestLayout();
            return;
        }
        Log.w("@@@", "Invalid mode");
    }
    
    public void setSurfaceMeasureListener(final SurfaceMeasureListener surfaceMeasureListener) {
        this.surfaceMeasureListener = surfaceMeasureListener;
    }
    
    public void setVideoHeight(final int mVideoHeight) {
        this.mVideoHeight = mVideoHeight;
    }
    
    public void setVideoWidth(final int mVideoWidth) {
        this.mVideoWidth = mVideoWidth;
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
