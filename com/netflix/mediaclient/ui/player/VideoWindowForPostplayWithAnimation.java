// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

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
        this.mParams = new VideoWindowForPostplayWithAnimation$ScaleAnimationParameters(500, 0, 0, 1.0f, AndroidUtils.dipToPixels((Context)this.mContext, 12), AndroidUtils.dipToPixels((Context)this.mContext, 12), AndroidUtils.dipToPixels((Context)this.mContext, 300) / DeviceUtils.getScreenWidthInPixels((Context)this.mContext));
    }
    
    @Override
    public void animateIn() {
        this.removeCenterInParent((View)this.mSurface);
        this.removeCenterInParent((View)this.mSurface2);
        if (this.mSurface != null) {
            this.mSurface.setBackgroundResource(2130837862);
            this.mSurface.setPadding(1, 1, 1, 1);
        }
        this.mOriginalSurfaceState = this.getCurrentSurfaceState();
        (this.mAnimator = new Thread(new VideoWindowForPostplayWithAnimation$ScaleAnimation(this, this.getTransitionToPostPlayAnimationParameters(), null, null))).start();
    }
    
    @Override
    protected VideoWindowForPostplayWithAnimation$ScaleAnimationParameters getTransitionToPostPlayAnimationParameters() {
        return this.mParams;
    }
}
