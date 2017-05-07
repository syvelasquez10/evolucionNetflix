// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import java.util.concurrent.atomic.AtomicBoolean;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.view.animation.Animation;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.animation.Animation$AnimationListener;
import com.netflix.mediaclient.ui.common.CurrentTimeAnimation;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import java.nio.ByteBuffer;

public class CurrentTimeTablet extends CurrentTime
{
    private CurrentTimeTablet$AnimationLock lock;
    
    CurrentTimeTablet(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.lock = new CurrentTimeTablet$AnimationLock(this, null);
    }
    
    private void startStartAnimation(final ByteBuffer byteBuffer) {
        this.context.getScreen().startBif(byteBuffer);
        this.currentTimeAnimationInProgress = true;
        final CurrentTimeAnimation currentTimeAnimation = new CurrentTimeAnimation(0, 0.0f, 0, 0.0f, 0, 0.0f, 0, -AndroidUtils.dipToPixels((Context)this.context, 22));
        currentTimeAnimation.setDuration(300L);
        final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
        currentTimeAnimation.setAnimationListener((Animation$AnimationListener)new CurrentTimeTablet$1(this, bottomPanel));
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.currentTime.getLayoutParams();
        layoutParams.setMargins(bottomPanel.getTimeX(this.currentTime), 0, 0, 18);
        this.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        if (this.currentTime.getVisibility() != 0) {
            this.currentTime.setVisibility(0);
        }
        this.updateCurrentTime();
        this.currentTime.startAnimation((Animation)currentTimeAnimation);
    }
    
    private void startStopAnimation(final boolean b) {
        this.context.getScreen().stopBif();
        this.currentTimeAnimationInProgress = true;
        final CurrentTimeAnimation currentTimeAnimation = new CurrentTimeAnimation(0, 0.0f, 0, 0.0f, 0, 0.0f, 0, AndroidUtils.dipToPixels((Context)this.context, 22));
        currentTimeAnimation.setDuration(300L);
        currentTimeAnimation.setFillAfter(true);
        currentTimeAnimation.setAnimationListener((Animation$AnimationListener)new CurrentTimeTablet$2(this, b));
        if (this.currentTime.getVisibility() != 0) {
            this.currentTime.setVisibility(0);
        }
        if (b) {
            this.updateCurrentTime();
        }
        this.currentTime.startAnimation((Animation)currentTimeAnimation);
    }
    
    @Override
    public void move(final boolean b, final boolean b2) {
        while (true) {
            while (true) {
                Label_0245: {
                    synchronized (this) {
                        if (this.currentTimeAnimationInProgress) {
                            Log.d("screen", "Current time animation in progress. Ignore!");
                        }
                        else {
                            Object context = this.context;
                            if (context != null && this.currentTime != null) {
                                if (b) {
                                    break Label_0245;
                                }
                                final int n = 40;
                                if (Log.isLoggable("screen", 3)) {
                                    Log.d("screen", "moveCurrentTimeWithTimeline start by margin: " + n);
                                }
                                final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
                                final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.currentTime.getLayoutParams();
                                final int dipToPixels = AndroidUtils.dipToPixels((Context)context, n);
                                layoutParams.setMargins(bottomPanel.getTimeX(this.currentTime), 0, 0, dipToPixels);
                                if (this.currentTime.getVisibility() != 0) {
                                    this.currentTime.setVisibility(0);
                                }
                                if (b2) {
                                    this.updateCurrentTime();
                                }
                                Log.d("screen", "moveCurrentTimeWithTimeline set layout parameter!");
                                this.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                                this.currentTime.clearAnimation();
                                context = this.currentTimeExp.getLayoutParams();
                                ((RelativeLayout$LayoutParams)context).setMargins(bottomPanel.getTimeX(this.currentTimeExp), 0, 0, dipToPixels);
                                this.currentTimeExp.setLayoutParams((ViewGroup$LayoutParams)context);
                                this.context.getScreen().getBottomPanel().getLastTime().validateTimeOverlap((View)this.currentTimeLabel);
                            }
                        }
                        return;
                    }
                }
                final int n = 18;
                continue;
            }
        }
    }
    
    @Override
    public void setBifDownloaded(final boolean bifDownloaded) {
        super.setBifDownloaded(bifDownloaded);
        if (this.mBifDownloaded.get()) {
            this.currentTime.setBackgroundResource(0);
        }
    }
    
    @Override
    public void start(final ByteBuffer byteBuffer) {
        while (true) {
            boolean b = true;
            Label_0062: {
                synchronized (this) {
                    final AtomicBoolean mBifDownloaded = this.mBifDownloaded;
                    if (byteBuffer == null) {
                        b = false;
                    }
                    mBifDownloaded.set(b);
                    if (this.context != null && this.currentTime != null) {
                        if (this.currentTimeAnimationInProgress) {
                            break Label_0062;
                        }
                        this.startStartAnimation(byteBuffer);
                    }
                    return;
                }
            }
            Log.d("screen", "Start animation is not completed yet!");
            this.currentTime.clearAnimation();
            final ByteBuffer byteBuffer2;
            this.startStartAnimation(byteBuffer2);
            this.lock.animationCanceled = true;
        }
    }
    
    @Override
    public void stop(final boolean b) {
        while (true) {
            Label_0041: {
                synchronized (this) {
                    if (this.context != null && this.currentTime != null) {
                        if (this.currentTimeAnimationInProgress) {
                            break Label_0041;
                        }
                        this.startStopAnimation(b);
                    }
                    return;
                }
            }
            Log.d("screen", "Start animation is not completed yet!");
            this.currentTime.clearAnimation();
            this.startStopAnimation(b);
            this.lock.animationCanceled = true;
        }
    }
}
