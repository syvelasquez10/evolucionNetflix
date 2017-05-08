// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.content.res.Resources;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import com.netflix.mediaclient.util.ThreadUtils;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.Collection;
import android.animation.AnimatorSet;
import android.animation.Animator;
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
import android.animation.ValueAnimator;
import android.view.View;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class WPMomentScreen$10 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ ObjectAnimator val$cardAnimator;
    final /* synthetic */ int val$cardIndex;
    final /* synthetic */ int val$color;
    final /* synthetic */ boolean val$open;
    final /* synthetic */ View val$panelView;
    final /* synthetic */ int val$size;
    
    WPMomentScreen$10(final WPMomentScreen this$0, final boolean val$open, final View val$panelView, final int val$color, final int val$cardIndex, final int val$size, final ObjectAnimator val$cardAnimator) {
        this.this$0 = this$0;
        this.val$open = val$open;
        this.val$panelView = val$panelView;
        this.val$color = val$color;
        this.val$cardIndex = val$cardIndex;
        this.val$size = val$size;
        this.val$cardAnimator = val$cardAnimator;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        if (valueAnimator.getAnimatedFraction() >= 0.5) {
            if (this.val$open) {
                this.val$panelView.setBackgroundColor(this.this$0.colorWhite);
            }
            else {
                this.val$panelView.setBackgroundColor(this.val$color);
            }
            if (this.val$cardIndex == this.val$size / 2) {
                this.this$0.manager.playPanelShuffleSound();
            }
            this.val$cardAnimator.removeUpdateListener((ValueAnimator$AnimatorUpdateListener)this);
        }
    }
}
