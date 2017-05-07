// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
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

public class NetflixSeekBar extends SeekBar
{
    public static final int DENT_DELTA = 2;
    public static final int DENT_DELTA_MEASURE = 100;
    private static final String TAG = "nf_seekbar";
    private Drawable cachedThumb;
    private Bitmap dent;
    private int dentPosition;
    private boolean dentVisible;
    private int progressBarPadding;
    private Rect rectDent;
    private boolean snapInProgress;
    private int snapPosition;
    
    public NetflixSeekBar(final Context context) {
        super(context);
        this.dentPosition = -1;
        this.snapPosition = -1;
        this.init();
    }
    
    public NetflixSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.dentPosition = -1;
        this.snapPosition = -1;
        this.init();
    }
    
    public NetflixSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.dentPosition = -1;
        this.snapPosition = -1;
        this.init();
    }
    
    private float computeXOffsetFromProgress(final int n) {
        return (this.getWidth() - this.getPaddingLeft() - this.getPaddingRight()) * n / this.getMax();
    }
    
    private void drawDent(final Canvas canvas) {
        if (this.dent != null) {
            if (this.rectDent == null) {
                this.rectDent = new Rect();
                final int n = (int)(this.cachedThumb.getBounds().centerX() - this.computeXOffsetFromProgress(this.getProgress() - this.snapPosition));
                final int width = this.dent.getWidth();
                final Rect bounds = this.getProgressDrawable().getBounds();
                this.rectDent.set(n, bounds.top + this.progressBarPadding, width + n, bounds.bottom - this.progressBarPadding);
                this.dentPosition = this.rectDent.centerX();
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
    
    private int getDentDelta() {
        return this.getMax() * 2 / 100;
    }
    
    private boolean inSnapZone(final int n) {
        final int dentDelta = this.getDentDelta();
        return n >= this.snapPosition - dentDelta && n <= dentDelta + this.snapPosition;
    }
    
    private void init() {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            Api21Util.setSplitTrack(this, false);
        }
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
    
    private boolean validateSnap(final int n) {
        if (this.inSnapZone(n)) {
            Log.d("nf_seekbar", "validateSnap:: snap in progress. Still in snap zone Ignore.");
            return false;
        }
        Log.d("nf_seekbar", "validateSnap:: snap in progress. Not in snap zone anymore.");
        this.snapInProgress = false;
        return true;
    }
    
    public boolean dispatchTrackballEvent(final MotionEvent motionEvent) {
        return this.onTrackballEvent(motionEvent);
    }
    
    public int getDentMiddleX() {
        return this.dentPosition;
    }
    
    public Drawable getNetflixThumb() {
        return this.cachedThumb;
    }
    
    public int getSnapPosition() {
        return this.snapPosition;
    }
    
    public boolean inSnapPosition() {
        return this.getProgress() == this.snapPosition;
    }
    
    public boolean isDentVisible() {
        return this.dentVisible;
    }
    
    public boolean isSnapInProgress() {
        synchronized (this) {
            return this.snapInProgress;
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        synchronized (this) {
            super.onDraw(canvas);
            if (this.dentVisible && this.dent != null) {
                this.drawDent(canvas);
                this.drawThumb(canvas);
            }
        }
    }
    
    void onKeyChange() {
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        boolean b = true;
        if ((n == 21 || n == 22) && this.snapInProgress) {
            final int progress = this.getProgress();
            switch (n) {
                case 21: {
                    if (progress <= 0) {
                        break;
                    }
                    if (this.validateSnap(progress - this.getKeyProgressIncrement())) {
                        return super.onKeyDown(n, keyEvent);
                    }
                    return b;
                }
                case 22: {
                    if (progress >= this.getMax()) {
                        break;
                    }
                    if (this.validateSnap(progress + this.getKeyProgressIncrement())) {
                        return super.onKeyDown(n, keyEvent);
                    }
                    return b;
                }
            }
            b = false;
            return b;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int touchEventToProgress = this.touchEventToProgress(motionEvent);
        if (motionEvent.getAction() == 2 && this.snapInProgress) {
            if (this.inSnapZone(touchEventToProgress)) {
                Log.d("nf_seekbar", "onTouchEvent snap in progress. Still in snap zone Ignore.");
                return false;
            }
            Log.d("nf_seekbar", "onTouchEvent snap in progress. Not in snap zone anymore.");
            this.snapInProgress = false;
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public boolean onTrackballEvent(final MotionEvent motionEvent) {
        return true;
    }
    
    @TargetApi(16)
    public boolean performAccessibilityAction(final int n, final Bundle bundle) {
        if ((n == 8192 || n == 4096) && this.snapInProgress) {
            final int progress = this.getProgress();
            final int max = Math.max(1, Math.round(this.getMax() / 5.0f));
            switch (n) {
                case 8192: {
                    if (progress > 0) {
                        return !this.validateSnap(progress - max) || super.performAccessibilityAction(n, bundle);
                    }
                    break;
                }
                case 4096: {
                    if (progress < this.getMax()) {
                        return !this.validateSnap(progress + max) || super.performAccessibilityAction(n, bundle);
                    }
                    break;
                }
            }
            return false;
        }
        return super.performAccessibilityAction(n, bundle);
    }
    
    public int setDentVisible(final boolean dentVisible) {
        int progress = -1;
        synchronized (this) {
            if (!(this.dentVisible = dentVisible)) {
                this.rectDent = null;
                this.snapPosition = -1;
            }
            else {
                this.dentPosition = -1;
                progress = this.getProgress();
                this.snapPosition = progress;
            }
            this.invalidate();
            return progress;
        }
    }
    
    public void setProgress(final int progress) {
        synchronized (this) {
            super.setProgress(progress);
        }
    }
    
    public void setProgressBarPadding(final int progressBarPadding) {
        this.progressBarPadding = progressBarPadding;
    }
    
    public void setScrubberDent(final Bitmap dent) {
        synchronized (this) {
            this.dent = dent;
        }
    }
    
    public void setThumb(final Drawable cachedThumb) {
        super.setThumb(this.cachedThumb = cachedThumb);
    }
    
    public void snapToLastTime() {
        synchronized (this) {
            Log.d("nf_seekbar", "Snap!!!");
            this.snapInProgress = true;
            super.setProgress(this.snapPosition);
        }
    }
}
