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
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPVideo;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.widget.FrameLayout;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.text.TextUtils;
import com.netflix.mediaclient.util.log.IkoLogUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class WPCardLayout$5 extends SimpleManagerCallback
{
    final /* synthetic */ WPCardLayout this$0;
    final /* synthetic */ String val$videoUrl;
    
    WPCardLayout$5(final WPCardLayout this$0, final String val$videoUrl) {
        this.this$0 = this$0;
        this.val$videoUrl = val$videoUrl;
    }
    
    @Override
    public void onResourceCached(final String s, final String s2, final long n, final long n2, final Status status) {
        super.onResourceCached(s, s2, n, n2, status);
        if (status.isError()) {
            if (Log.isLoggable()) {
                Log.e("WPCardLayout", "Failed to retrieve video: " + s);
            }
            final Error error = status.getError();
            List<DeepErrorElement> deepError;
            if (error != null) {
                deepError = error.getDeepError();
            }
            else {
                deepError = null;
            }
            IkoLogUtils.reportIkoVideoLoadEnded(this.this$0.getContext(), IClientLogging$CompletionReason.failed, new UIError(RootCause.clientFailure, ActionOnUIError.handledSilently, null, deepError));
            this.this$0.onVideoPlaybackComplete();
        }
        else if (this.this$0.mediaPlayerWrapper != null && !TextUtils.isEmpty((CharSequence)s2)) {
            if (Log.isLoggable()) {
                Log.v("WPCardLayout", "Downloaded video - localUrl: " + s2);
            }
            final Context context = this.this$0.getContext();
            IkoLogUtils.reportIkoVideoPlaybackStarted(context, this.val$videoUrl);
            IkoLogUtils.reportIkoVideoLoadEnded(context, IClientLogging$CompletionReason.success, (UIError)null);
            this.this$0.mediaPlayerWrapper.setDataSource(s2, n, n2);
            this.this$0.showVideo();
        }
    }
}
