// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.common.CurrentTimeAnimation;

public class CurrentTimeTablet extends CurrentTime
{
    private static final String TAG = "CurrentTimeTablet";
    private final int bottomMarginInPx;
    private CurrentTimeAnimation endAnimation;
    private CurrentTimeAnimation startAnimation;
    
    CurrentTimeTablet(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.bottomMarginInPx = AndroidUtils.dipToPixels((Context)playerFragment.getActivity(), 40);
    }
    
    private void startStartAnimation(final ByteBuffer byteBuffer) {
        Log.d("CurrentTimeTablet", "startStartAnimation()");
        if (this.startAnimation == null) {
            (this.startAnimation = new CurrentTimeAnimation(this.currentTime, 0, -this.bottomMarginInPx)).addListener((Animator$AnimatorListener)new CurrentTimeTablet$1(this, byteBuffer));
        }
        this.playerFragment.getScreen().showBif(byteBuffer);
        this.startAnimation.start();
    }
    
    private void startStopAnimation(final boolean b) {
        Log.d("CurrentTimeTablet", "startStopAnimation()");
        if (this.endAnimation == null) {
            (this.endAnimation = new CurrentTimeAnimation(this.currentTime, -this.bottomMarginInPx, 0)).addListener((Animator$AnimatorListener)new CurrentTimeTablet$2(this, b));
        }
        this.endAnimation.start();
        this.playerFragment.getScreen().stopBif();
    }
    
    @Override
    public void start(final ByteBuffer byteBuffer) {
        if (Log.isLoggable()) {
            Log.d("CurrentTimeTablet", "start: bb " + byteBuffer);
        }
        this.mBifDownloaded.set(byteBuffer != null);
        if (this.playerFragment == null || this.currentTime == null) {
            return;
        }
        this.updateCurrentTime();
        this.startStartAnimation(byteBuffer);
        Log.i("CurrentTimeTablet", "start end");
    }
    
    @Override
    public void stop(final boolean b) {
        Log.d("CurrentTimeTablet", "stop");
        if (this.playerFragment == null || this.currentTime == null) {
            return;
        }
        if (this.startAnimation != null && this.startAnimation.isRunning()) {
            Log.d("CurrentTimeTablet", "Start animation is not completed yet - cancelling it");
            this.startAnimation.cancel();
            this.playerFragment.getScreen().stopBif();
            this.currentTime.setTranslationY(0.0f);
        }
        else {
            if (b) {
                this.updateCurrentTime();
            }
            this.startStopAnimation(b);
        }
        Log.i("CurrentTimeTablet", "stop end");
    }
    
    @Override
    public void updateTimeMargins() {
        Log.i("CurrentTimeTablet", "doUpdateTimeMargins");
        if (!this.playerFragment.isActivityValid() || this.currentTime == null) {
            return;
        }
        final BottomPanel bottomPanel = this.playerFragment.getScreen().getBottomPanel();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.currentTime.getLayoutParams();
        layoutParams.setMargins(bottomPanel.getTimeXAndUpdateHandler(this.currentTime), 0, 0, layoutParams.bottomMargin);
        this.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
}
