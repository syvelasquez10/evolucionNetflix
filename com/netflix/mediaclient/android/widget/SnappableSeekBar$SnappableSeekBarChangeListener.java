// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.BitmapFactory;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.api.Api21Util;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.MathUtils;
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.MathUtils$Range;
import android.widget.SeekBar$OnSeekBarChangeListener;

class SnappableSeekBar$SnappableSeekBarChangeListener implements SeekBar$OnSeekBarChangeListener
{
    private MathUtils$Range progressSnapRegion;
    final /* synthetic */ SnappableSeekBar this$0;
    private final SnappableSeekBar$OnSnappableSeekBarChangeListener wrappedListener;
    
    public SnappableSeekBar$SnappableSeekBarChangeListener(final SnappableSeekBar this$0, final SnappableSeekBar$OnSnappableSeekBarChangeListener wrappedListener) {
        this.this$0 = this$0;
        this.wrappedListener = wrappedListener;
    }
    
    private MathUtils$Range computeProgressSnapRegion() {
        final int n = AndroidUtils.dipToPixels(this.this$0.getContext(), 24) * this.this$0.getMax() / this.this$0.getWidth();
        final int progress = this.this$0.getProgress();
        final MathUtils$Range mathUtils$Range = new MathUtils$Range(progress - n, n + progress);
        if (Log.isLoggable("SnappableSeekBar", 2)) {
            Log.v("SnappableSeekBar", "snap region: " + mathUtils$Range + ", position: " + progress + ", max position: " + this.this$0.getMax());
        }
        return mathUtils$Range;
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
        MathUtils$Range computeProgressSnapRegion;
        if (this.this$0.shouldSnapToTouchStartPosition) {
            computeProgressSnapRegion = this.computeProgressSnapRegion();
        }
        else {
            computeProgressSnapRegion = null;
        }
        this.progressSnapRegion = computeProgressSnapRegion;
        this.this$0.startSnapping();
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        Log.v("SnappableSeekBar", "onStopTrackingTouch");
        this.wrappedListener.onStopTrackingTouch(seekBar, this.progressSnapRegion != null && this.progressSnapRegion.contains(this.this$0.getProgress()));
        this.progressSnapRegion = null;
        this.this$0.finishSnapping();
    }
}
