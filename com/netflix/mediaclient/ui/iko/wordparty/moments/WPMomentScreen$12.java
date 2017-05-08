// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPImage;
import com.netflix.mediaclient.util.ThreadUtils;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.ViewUtils;
import java.util.Collection;
import android.animation.AnimatorSet;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPItem;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.view.ViewGroup;
import android.os.Handler;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import java.util.List;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPMomentScreen$12 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ boolean val$open;
    
    WPMomentScreen$12(final WPMomentScreen this$0, final boolean val$open) {
        this.this$0 = this$0;
        this.val$open = val$open;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startPanelAnimation onAnimationEnd");
        }
        if (!this.this$0.isMomentClosed()) {
            if (this.val$open) {
                this.this$0.showCurrentLearnMomentCard();
                return;
            }
            if (this.this$0.cardsList != null && !this.this$0.cardsList.isEmpty()) {
                this.this$0.startPanelAnimation(!this.val$open);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("WPMomentScreen", "startPanelAnimation onAnimationEnd: cardsList is null or empty");
            }
        }
    }
}
