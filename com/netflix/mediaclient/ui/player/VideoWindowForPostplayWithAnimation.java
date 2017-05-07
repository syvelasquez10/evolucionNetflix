// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.os.Process;
import android.view.View;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;

public class VideoWindowForPostplayWithAnimation extends VideoWindowForPostplayWithScaling
{
    protected static final int ANIMATION_DURATION = 500;
    private final int END_MARGIN_LEFT_DP;
    private final int END_MARGIN_TOP_DP;
    private final int END_WIDTH_DP;
    private Thread mAnimator;
    
    public VideoWindowForPostplayWithAnimation(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.END_MARGIN_TOP_DP = 12;
        this.END_MARGIN_LEFT_DP = 12;
        this.END_WIDTH_DP = 300;
        Log.d(VideoWindowForPostplayWithAnimation.TAG, "PostPlayWithAnimation");
        this.init();
    }
    
    private void init() {
        this.mParams = new ScaleAnimationParameters(500, 0, 0, 1.0f, AndroidUtils.dipToPixels((Context)this.mContext, 12), AndroidUtils.dipToPixels((Context)this.mContext, 12), AndroidUtils.dipToPixels((Context)this.mContext, 300) / DeviceUtils.getScreenWidthInPixels((Context)this.mContext));
    }
    
    @Override
    public void animateIn() {
        this.removeCenterInParent((View)this.mSurface);
        this.removeCenterInParent((View)this.mSurface2);
        if (this.mSurface != null) {
            this.mSurface.setBackgroundResource(2130837821);
            this.mSurface.setPadding(1, 1, 1, 1);
        }
        this.mOriginalSurfaceState = this.getCurrentSurfaceState();
        (this.mAnimator = new Thread(new ScaleAnimation(this.getTransitionToPostPlayAnimationParameters(), (Runnable)null))).start();
    }
    
    @Override
    protected ScaleAnimationParameters getTransitionToPostPlayAnimationParameters() {
        return this.mParams;
    }
    
    private class ScaleAnimation implements Runnable
    {
        private final int REFRESH_RATE;
        private Runnable mExecuteOnEndOfAnimation;
        private ScaleAnimationParameters mParams;
        private boolean mRunning;
        
        private ScaleAnimation(final ScaleAnimationParameters mParams, final Runnable mExecuteOnEndOfAnimation) {
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
                    if (Log.isLoggable(VideoWindowForPostplayWithScaling.TAG, 3)) {
                        Log.d(VideoWindowForPostplayWithScaling.TAG, "Count # " + n);
                        Log.d(VideoWindowForPostplayWithScaling.TAG, "left: " + n9);
                        Log.d(VideoWindowForPostplayWithScaling.TAG, "top: " + n10);
                        Log.d(VideoWindowForPostplayWithScaling.TAG, "scale: " + n11);
                    }
                    VideoWindowForPostplayWithAnimation.this.resizeVideo(n9, n10, n11);
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
            VideoWindowForPostplayWithAnimation.this.resizeVideo(this.mParams.getEndLeftMargin(), this.mParams.getEndTopMargin(), this.mParams.getEndScale());
            if (this.mExecuteOnEndOfAnimation != null) {
                VideoWindowForPostplayWithAnimation.this.mContext.runInUiThread(this.mExecuteOnEndOfAnimation);
            }
        }
    }
    
    protected static class ScaleAnimationParameters
    {
        private int mDurationInMS;
        private int mEndLeftMargin;
        private float mEndScale;
        private int mEndTopMargin;
        private int mStartLeftMargin;
        private float mStartScale;
        private int mStartTopMargin;
        
        public ScaleAnimationParameters(final int mDurationInMS, final int mStartLeftMargin, final int mStartTopMargin, final float mStartScale, final int mEndLeftMargin, final int mEndTopMargin, final float mEndScale) {
            this.mDurationInMS = mDurationInMS;
            this.mStartLeftMargin = mStartLeftMargin;
            this.mStartTopMargin = mStartTopMargin;
            this.mStartScale = mStartScale;
            this.mEndLeftMargin = mEndLeftMargin;
            this.mEndTopMargin = mEndTopMargin;
            this.mEndScale = mEndScale;
        }
        
        public int getDurationInMS() {
            return this.mDurationInMS;
        }
        
        public int getEndLeftMargin() {
            return this.mEndLeftMargin;
        }
        
        public float getEndScale() {
            return this.mEndScale;
        }
        
        public int getEndTopMargin() {
            return this.mEndTopMargin;
        }
        
        public int getStartLeftMargin() {
            return this.mStartLeftMargin;
        }
        
        public float getStartScale() {
            return this.mStartScale;
        }
        
        public int getStartTopMargin() {
            return this.mStartTopMargin;
        }
    }
}
