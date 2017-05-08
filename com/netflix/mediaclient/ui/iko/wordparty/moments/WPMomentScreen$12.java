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
import com.netflix.mediaclient.Log;
import java.util.List;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;

class WPMomentScreen$12 implements BaseInteractiveMomentsManager$PlaybackCompleteListener
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ List val$audioList;
    final /* synthetic */ int val$counter;
    final /* synthetic */ WPMomentScreen$WordPartyMomentState val$nextState;
    final /* synthetic */ int val$size;
    
    WPMomentScreen$12(final WPMomentScreen this$0, final WPMomentScreen$WordPartyMomentState val$nextState, final int val$counter, final int val$size, final List val$audioList) {
        this.this$0 = this$0;
        this.val$nextState = val$nextState;
        this.val$counter = val$counter;
        this.val$size = val$size;
        this.val$audioList = val$audioList;
    }
    
    @Override
    public void onComplete(final String s) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "playVOList: onComplete url = " + s + " currentState = " + this.this$0.currentState + " nextState = " + this.val$nextState);
        }
        if (this.val$counter + 1 < this.val$size) {
            this.this$0.playAudioList(this.val$audioList, this.val$counter + 1, this.val$nextState);
            return;
        }
        this.this$0.moveToState(this.val$nextState);
    }
}
