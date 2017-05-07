// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.MathUtils;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;

public class SnappableSeekBar extends SeekBar
{
    private static final int SNAP_REGION_SIZE_DP = 48;
    private static final String TAG = "SnappableSeekBar";
    private Drawable cachedThumb;
    private Bitmap dent;
    private boolean dentVisible;
    private boolean disableTrackTouching;
    private Rect rectDent;
    private boolean shouldSnapToTouchStartPosition;
    private SnappableSeekBarChangeListener snapListener;
    private int snapPosition;
    
    public SnappableSeekBar(final Context context) {
        super(context);
        this.snapPosition = -1;
    }
    
    public SnappableSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.snapPosition = -1;
    }
    
    public SnappableSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.snapPosition = -1;
    }
    
    private float computeXOffsetFromProgress(final int n) {
        return (this.getWidth() - this.getPaddingLeft() - this.getPaddingRight()) * n / this.getMax();
    }
    
    private void drawDent(final Canvas canvas) {
        if (this.dent != null) {
            if (this.rectDent == null) {
                final int n = (int)(this.computeXOffsetFromProgress(this.snapPosition) + 0.5f) + this.getPaddingLeft() + this.getThumbOffset() / 2 - this.dent.getWidth() / 2;
                final int width = this.dent.getWidth();
                final int centerY = this.getProgressDrawable().getBounds().centerY();
                this.rectDent = new Rect(n, centerY - this.dent.getHeight() / 2, n + width, centerY + this.dent.getHeight() / 2);
            }
            canvas.save();
            canvas.translate((float)(this.getPaddingLeft() - this.getThumbOffset()), (float)this.getPaddingTop());
            canvas.drawBitmap(this.dent, (Rect)null, this.rectDent, (Paint)null);
            canvas.restore();
        }
    }
    
    private void drawThumb(final Canvas canvas) {
        if (this.cachedThumb != null) {
            canvas.save();
            canvas.translate((float)(this.getPaddingLeft() - this.getThumbOffset()), (float)this.getPaddingTop());
            this.cachedThumb.draw(canvas);
            canvas.restore();
        }
    }
    
    private void finishSnapping() {
        this.dentVisible = false;
        this.rectDent = null;
        this.snapPosition = -1;
        this.invalidate();
    }
    
    private void startSnapping() {
        this.dentVisible = true;
        this.snapPosition = this.getProgress();
        this.invalidate();
    }
    
    private int touchEventToProgress(final MotionEvent motionEvent) {
        final int width = this.getWidth();
        final int paddingLeft = this.getPaddingLeft();
        final int paddingRight = this.getPaddingRight();
        final int n = (int)motionEvent.getX();
        float n2;
        if (n < this.getPaddingLeft()) {
            n2 = 0.0f;
        }
        else if (n > width - this.getPaddingRight()) {
            n2 = 1.0f;
        }
        else {
            n2 = (n - this.getPaddingLeft()) / (width - paddingLeft - paddingRight);
        }
        return (int)(0.0f + this.getMax() * n2);
    }
    
    public Drawable getCachedThumb() {
        return this.cachedThumb;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.dentVisible && this.dent != null) {
            this.drawDent(canvas);
            this.drawThumb(canvas);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        boolean contains = false;
        if (this.disableTrackTouching) {
            final Drawable cachedThumb = this.getCachedThumb();
            contains = contains;
            if (cachedThumb != null) {
                final Rect rect = new Rect(cachedThumb.getBounds());
                rect.left -= this.getThumbOffset();
                rect.right -= this.getThumbOffset();
                contains = rect.contains((int)motionEvent.getX(), (int)motionEvent.getY());
            }
        }
        boolean b;
        if (this.disableTrackTouching && !contains && motionEvent.getAction() == 0) {
            b = false;
        }
        else {
            final boolean onTouchEvent = super.onTouchEvent(motionEvent);
            final int touchEventToProgress = this.touchEventToProgress(motionEvent);
            if (motionEvent.getAction() != 2) {
                b = onTouchEvent;
                if (motionEvent.getAction() != 1) {
                    return b;
                }
            }
            b = onTouchEvent;
            if (this.snapListener != null) {
                b = onTouchEvent;
                if (this.snapListener.isWithinInternalSnapRegion(touchEventToProgress)) {
                    this.setProgress(this.snapPosition);
                    return onTouchEvent;
                }
            }
        }
        return b;
    }
    
    public void setDisableTrackTouching(final boolean disableTrackTouching) {
        this.disableTrackTouching = disableTrackTouching;
    }
    
    public void setScrubberDentBitmap(final int n) {
        this.dent = BitmapFactory.decodeResource(this.getResources(), n);
        Log.v("SnappableSeekBar", "Dent: " + this.dent.getWidth() + "x" + this.dent.getHeight());
    }
    
    public void setShouldSnapToTouchStartPosition(final boolean shouldSnapToTouchStartPosition) {
        this.shouldSnapToTouchStartPosition = shouldSnapToTouchStartPosition;
    }
    
    public void setSnappableOnSeekBarChangeListener(final OnSnappableSeekBarChangeListener onSnappableSeekBarChangeListener) {
        super.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)(this.snapListener = new SnappableSeekBarChangeListener(onSnappableSeekBarChangeListener)));
    }
    
    public void setThumb(final Drawable cachedThumb) {
        super.setThumb(this.cachedThumb = cachedThumb);
    }
    
    public interface OnSnappableSeekBarChangeListener
    {
        void onProgressChanged(final SeekBar p0, final int p1, final boolean p2);
        
        void onStartTrackingTouch(final SeekBar p0);
        
        void onStopTrackingTouch(final SeekBar p0, final boolean p1);
    }
    
    private class SnappableSeekBarChangeListener implements SeekBar$OnSeekBarChangeListener
    {
        private MathUtils.Range progressSnapRegion;
        private final OnSnappableSeekBarChangeListener wrappedListener;
        
        public SnappableSeekBarChangeListener(final OnSnappableSeekBarChangeListener wrappedListener) {
            this.wrappedListener = wrappedListener;
        }
        
        private MathUtils.Range computeProgressSnapRegion() {
            final int n = AndroidUtils.dipToPixels(SnappableSeekBar.this.getContext(), 24) * SnappableSeekBar.this.getMax() / SnappableSeekBar.this.getWidth();
            final int progress = SnappableSeekBar.this.getProgress();
            final MathUtils.Range range = new MathUtils.Range(progress - n, progress + n);
            if (Log.isLoggable("SnappableSeekBar", 2)) {
                Log.v("SnappableSeekBar", "snap region: " + range + ", position: " + progress + ", max position: " + SnappableSeekBar.this.getMax());
            }
            return range;
        }
        
        private boolean isWithinInternalSnapRegion(final int n) {
            return this.progressSnapRegion != null && this.progressSnapRegion.contains(n);
        }
        
        public void onProgressChanged(final SeekBar seekBar, int constrain, final boolean b) {
            Log.v("SnappableSeekBar", "onProgressChanged, progress: " + constrain + ", fromUser: " + b);
            int n = constrain;
            if (b) {
                n = constrain;
                if (this.isWithinInternalSnapRegion(constrain)) {
                    constrain = MathUtils.constrain(this.progressSnapRegion.getMidpoint(), 0, seekBar.getMax());
                    seekBar.setProgress(constrain);
                    n = constrain;
                    if (Log.isLoggable("SnappableSeekBar", 2)) {
                        Log.v("SnappableSeekBar", "Progress is within snap region - snapping to: " + constrain);
                        n = constrain;
                    }
                }
            }
            this.wrappedListener.onProgressChanged(seekBar, n, b);
        }
        
        public void onStartTrackingTouch(final SeekBar seekBar) {
            Log.v("SnappableSeekBar", "onStartTrackingTouch");
            this.wrappedListener.onStartTrackingTouch(seekBar);
            MathUtils.Range computeProgressSnapRegion;
            if (SnappableSeekBar.this.shouldSnapToTouchStartPosition) {
                computeProgressSnapRegion = this.computeProgressSnapRegion();
            }
            else {
                computeProgressSnapRegion = null;
            }
            this.progressSnapRegion = computeProgressSnapRegion;
            SnappableSeekBar.this.startSnapping();
        }
        
        public void onStopTrackingTouch(final SeekBar seekBar) {
            Log.v("SnappableSeekBar", "onStopTrackingTouch");
            this.wrappedListener.onStopTrackingTouch(seekBar, this.progressSnapRegion != null && this.progressSnapRegion.contains(SnappableSeekBar.this.getProgress()));
            this.progressSnapRegion = null;
            SnappableSeekBar.this.finishSnapping();
        }
    }
}
