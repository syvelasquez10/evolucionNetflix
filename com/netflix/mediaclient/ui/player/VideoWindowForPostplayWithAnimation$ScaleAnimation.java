// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.os.Process;

class VideoWindowForPostplayWithAnimation$ScaleAnimation implements Runnable
{
    private final int REFRESH_RATE;
    private Runnable mExecuteOnEndOfAnimation;
    private VideoWindowForPostplayWithAnimation$ScaleAnimationParameters mParams;
    private boolean mRunning;
    final /* synthetic */ VideoWindowForPostplayWithAnimation this$0;
    
    private VideoWindowForPostplayWithAnimation$ScaleAnimation(final VideoWindowForPostplayWithAnimation this$0, final VideoWindowForPostplayWithAnimation$ScaleAnimationParameters mParams, final Runnable mExecuteOnEndOfAnimation) {
        this.this$0 = this$0;
        this.REFRESH_RATE = 20;
        if (mParams == null) {
            throw new IllegalArgumentException("Parameters can not be null");
        }
        this.mParams = mParams;
        this.mExecuteOnEndOfAnimation = mExecuteOnEndOfAnimation;
    }
    
    private float calculateOffset(final float n, final float n2, final float n3) {
        return n * n2 * n3;
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(-1);
        final long currentTimeMillis = System.currentTimeMillis();
        int n = 0;
        final int n2 = this.mParams.getDurationInMS() / 20;
        final float n3 = Math.abs(this.mParams.getStartTopMargin() - this.mParams.getEndTopMargin()) / n2;
        final float n4 = Math.abs(this.mParams.getStartLeftMargin() - this.mParams.getEndLeftMargin()) / n2;
        final float n5 = Math.abs(this.mParams.getStartScale() - this.mParams.getEndScale()) / n2;
        int n6 = 1;
        if (this.mParams.getStartTopMargin() > this.mParams.getEndTopMargin()) {
            n6 = -1;
        }
        int n7 = 1;
        if (this.mParams.getStartLeftMargin() > this.mParams.getEndLeftMargin()) {
            n7 = -1;
        }
        int n8 = 1;
        if (this.mParams.getStartScale() < this.mParams.getEndScale()) {
            n8 = -1;
        }
        this.mRunning = true;
        while (this.mRunning) {
            Log.d(VideoWindowForPostplayWithScaling.TAG, "in loop");
            if (System.currentTimeMillis() - currentTimeMillis >= this.mParams.getDurationInMS()) {
                Log.d(VideoWindowForPostplayWithScaling.TAG, "Exit!");
                this.mRunning = false;
            }
            else {
                ++n;
                final int n9 = this.mParams.getStartLeftMargin() + (int)this.calculateOffset(n4, n, n7);
                final int n10 = this.mParams.getStartTopMargin() + (int)this.calculateOffset(n3, n, n6);
                final float n11 = this.mParams.getStartScale() - n * n5 * n8;
                if (Log.isLoggable()) {
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "Count # " + n);
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "left: " + n9);
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "top: " + n10);
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "scale: " + n11);
                }
                this.this$0.resizeVideo(n9, n10, n11);
                try {
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "Sleep");
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex) {
                    Log.d(VideoWindowForPostplayWithScaling.TAG, "Interupped");
                    this.mRunning = false;
                }
            }
        }
        this.this$0.resizeVideo(this.mParams.getEndLeftMargin(), this.mParams.getEndTopMargin(), this.mParams.getEndScale());
        if (this.mExecuteOnEndOfAnimation != null) {
            this.this$0.mContext.runInUiThread(this.mExecuteOnEndOfAnimation);
        }
    }
}
