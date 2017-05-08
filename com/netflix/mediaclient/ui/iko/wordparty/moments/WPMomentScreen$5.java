// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.content.res.Resources;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import com.netflix.mediaclient.util.ThreadUtils;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import java.util.Collection;
import android.animation.AnimatorSet;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPItem;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.os.Handler;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import java.util.List;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPMomentScreen$5 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ WPCardLayout val$cardView;
    
    WPMomentScreen$5(final WPMomentScreen this$0, final WPCardLayout val$cardView) {
        this.this$0 = this$0;
        this.val$cardView = val$cardView;
    }
    
    public void onAnimationEnd(final Animator animator) {
        super.onAnimationEnd(animator);
        this.this$0.cardsList.remove(this.val$cardView);
        if (this.this$0.isMomentClosed()) {
            return;
        }
        ViewUtils.setVisibleOrGone((View)this.val$cardView, false);
        this.val$cardView.setAlpha(1.0f);
        this.this$0.startPanelAnimation(false);
        this.this$0.discardAnimationComplete();
    }
}
