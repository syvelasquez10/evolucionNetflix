// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.BounceInterpolator;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.util.OnAnimationEndListener;
import java.util.List;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import com.netflix.mediaclient.Log;

class KongUnlockScreen$1 implements Runnable
{
    final /* synthetic */ KongUnlockScreen this$0;
    
    KongUnlockScreen$1(final KongUnlockScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.postPlayManager.isPostPlayPaused()) {
            Log.d("KongUnlockScreen", "Post play is in paused state. Ignoring request to start unlock animation.");
            return;
        }
        this.this$0.startAnimation(this.this$0.gear1Item);
    }
}
