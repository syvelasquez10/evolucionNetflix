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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.iko.BaseInteractiveMomentsManager$PlaybackCompleteListener;

class WPMomentScreen$14$1 implements BaseInteractiveMomentsManager$PlaybackCompleteListener
{
    final /* synthetic */ WPMomentScreen$14 this$1;
    
    WPMomentScreen$14$1(final WPMomentScreen$14 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onComplete(final String s) {
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "startRecapAnimation playVO onComplete ");
        }
        if (StringUtils.isNotEmpty(s)) {
            this.this$1.this$0.currentlyPlayingAudioList.remove(s);
        }
        if (this.this$1.this$0.isMomentClosed()) {
            return;
        }
        this.this$1.this$0.playRecapItem(this.this$1.val$counter + 1);
    }
}
