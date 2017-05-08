// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import java.util.List;
import android.widget.TextView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class KongGearSelectionScreen$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    KongGearSelectionScreen$1(final KongGearSelectionScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.postPlayManager.setTitleText(this.this$0.itemSelectionHeaderString);
    }
}
