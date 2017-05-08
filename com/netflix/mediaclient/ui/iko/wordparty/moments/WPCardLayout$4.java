// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.util.Property;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.AnimatorSet;
import android.animation.Animator$AnimatorListener;
import android.net.Uri;
import android.text.TextUtils;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.view.TextureView;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPVideo;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.widget.FrameLayout;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.log.IkoLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;

class WPCardLayout$4 implements MediaPlayerWrapper$PlaybackEventsListener
{
    final /* synthetic */ WPCardLayout this$0;
    
    WPCardLayout$4(final WPCardLayout this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPlaybackError(final int n, final int n2) {
        IkoLogUtils.reportIkoVideoPlaybackEnded(this.this$0.getContext(), IClientLogging$CompletionReason.failed, n, n2);
    }
    
    @Override
    public void onPlaybackFinished() {
        if (Log.isLoggable()) {
            Log.d("WPCardLayout", "Video completed (or failed) - hiding TextureView");
        }
        if (this.this$0.mediaPlayerWrapper != null) {
            this.this$0.mediaPlayerWrapper.clearCallbacks();
        }
        this.this$0.hideVideo();
        this.this$0.onVideoPlaybackComplete();
    }
    
    @Override
    public void onPlaybackStarted() {
        this.this$0.startVideoPlaybackRevealAnimation();
    }
    
    @Override
    public void onPlaybackSuccessfullyCompleted() {
        IkoLogUtils.reportIkoVideoPlaybackEnded(this.this$0.getContext(), IClientLogging$CompletionReason.success, -1, -1);
    }
}
