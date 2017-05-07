// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.util.MathUtils$Range;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.api.Api21Util;
import com.netflix.mediaclient.util.AndroidUtils;
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
    private SnappableSeekBar$SnappableSeekBarChangeListener snapListener;
    private int snapPosition;
    
    public SnappableSeekBar(final Context context) {
        super(context);
        this.snapPosition = -1;
        this.init();
    }
    
    public SnappableSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.snapPosition = -1;
        this.init();
    }
    
    public SnappableSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.snapPosition = -1;
        this.init();
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
                this.rectDent = new Rect(n, centerY - this.dent.getHeight() / 2, width + n, this.dent.getHeight() / 2 + centerY);
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
    
    private void init() {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            Api21Util.setSplitTrack(this, false);
        }
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
        return (int)(n2 * this.getMax() + 0.0f);
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
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean b = false;
        while (true) {
            Label_0171: {
                if (!this.disableTrackTouching) {
                    break Label_0171;
                }
                final Drawable cachedThumb = this.getCachedThumb();
                if (cachedThumb == null) {
                    break Label_0171;
                }
                final Rect rect = new Rect(cachedThumb.getBounds());
                rect.left -= this.getThumbOffset();
                rect.right -= this.getThumbOffset();
                final int contains = rect.contains((int)motionEvent.getX(), (int)motionEvent.getY()) ? 1 : 0;
                boolean b2;
                if (this.disableTrackTouching && contains == 0 && motionEvent.getAction() == 0) {
                    b2 = b;
                }
                else {
                    final boolean onTouchEvent = super.onTouchEvent(motionEvent);
                    final int touchEventToProgress = this.touchEventToProgress(motionEvent);
                    if (motionEvent.getAction() != 2) {
                        b2 = onTouchEvent;
                        if (motionEvent.getAction() != 1) {
                            return b2;
                        }
                    }
                    b2 = onTouchEvent;
                    if (this.snapListener != null) {
                        b2 = onTouchEvent;
                        if (this.snapListener.isWithinInternalSnapRegion(touchEventToProgress)) {
                            this.setProgress(this.snapPosition);
                            return onTouchEvent;
                        }
                    }
                }
                return b2;
            }
            final int contains = 0;
            continue;
        }
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
    
    public void setSnappableOnSeekBarChangeListener(final SnappableSeekBar$OnSnappableSeekBarChangeListener snappableSeekBar$OnSnappableSeekBarChangeListener) {
        super.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)(this.snapListener = new SnappableSeekBar$SnappableSeekBarChangeListener(this, snappableSeekBar$OnSnappableSeekBarChangeListener)));
    }
    
    public void setThumb(final Drawable cachedThumb) {
        super.setThumb(this.cachedThumb = cachedThumb);
    }
}
