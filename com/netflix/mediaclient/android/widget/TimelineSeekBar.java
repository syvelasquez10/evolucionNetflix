// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.SeekBar;
import com.netflix.mediaclient.ui.player.PlayerFragment$VideoPositionListener;
import com.netflix.mediaclient.Log;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.SeekBar$OnSeekBarChangeListener;

public class TimelineSeekBar extends NetflixSeekBar
{
    private static final String TAG = "nf_timelineseekbar";
    private SeekBar$OnSeekBarChangeListener onSeekBarChangeListener;
    
    public TimelineSeekBar(final Context context) {
        super(context);
    }
    
    public TimelineSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public TimelineSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
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
    
    public float computeXOffsetFromProgress(final long n) {
        return (this.getWidth() - this.getPaddingLeft() - this.getPaddingRight()) * n / this.getMax();
    }
    
    public boolean dispatchTrackballEvent(final MotionEvent motionEvent) {
        return this.onTrackballEvent(motionEvent);
    }
    
    public void hideThumb(final boolean b) {
        if (Build$VERSION.SDK_INT >= 16) {
            final Drawable thumb = this.getThumb();
            int alpha;
            if (b) {
                alpha = 0;
            }
            else {
                alpha = 255;
            }
            thumb.setAlpha(alpha);
            return;
        }
        Drawable thumb2;
        if (b) {
            thumb2 = this.getResources().getDrawable(2131558590);
        }
        else {
            thumb2 = this.getResources().getDrawable(2130837908);
        }
        this.setThumb(thumb2);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int touchEventToProgress = this.touchEventToProgress(motionEvent);
        if (Log.isLoggable()) {
            Log.d("nf_timelineseekbar", "onTouchEvent: " + motionEvent.getAction());
        }
        return (this.onSeekBarChangeListener instanceof PlayerFragment$VideoPositionListener && ((PlayerFragment$VideoPositionListener)this.onSeekBarChangeListener).handleTouchEvent(this, motionEvent, touchEventToProgress)) || super.onTouchEvent(motionEvent);
    }
    
    public boolean onTrackballEvent(final MotionEvent motionEvent) {
        return true;
    }
    
    public void setOnSeekBarChangeListener(final SeekBar$OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(this.onSeekBarChangeListener = onSeekBarChangeListener);
    }
}
