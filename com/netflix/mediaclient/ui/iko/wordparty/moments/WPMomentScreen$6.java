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
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.view.View;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPItem;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.os.Handler;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPMoment;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.graphics.Bitmap;
import java.util.List;
import com.netflix.mediaclient.Log;

class WPMomentScreen$6 implements Runnable
{
    final /* synthetic */ WPMomentScreen this$0;
    
    WPMomentScreen$6(final WPMomentScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "timeoutRunnable: counter = " + this.this$0.timeoutCounter);
        }
        if (this.this$0.timeoutCounter >= 2) {
            this.this$0.playVOList(this.this$0.passiveExitVOList, WPMomentScreen$WordPartyMomentState.OUTRO);
            return;
        }
        final WPMomentScreen this$0 = this.this$0;
        List list;
        if (this.this$0.timeoutCounter == 0) {
            list = this.this$0.timeoutVOList;
        }
        else {
            list = this.this$0.timeout2VOList;
        }
        this$0.playVOList(list, WPMomentScreen$WordPartyMomentState.ITEM_SELECTION);
        this.this$0.timeoutCounter++;
        this.this$0.startWiggleAnimation();
    }
}
