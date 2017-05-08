// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.Log;
import java.util.Date;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ViewParent;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.text.Html;
import android.view.View$OnClickListener;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
import android.view.ContextThemeWrapper;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaCta;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.View$OnTouchListener;
import android.text.method.LinkMovementMethod;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.Space;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewPropertyAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class UserMessageAreaView$3 extends AnimatorListenerAdapter
{
    final /* synthetic */ UserMessageAreaView this$0;
    
    UserMessageAreaView$3(final UserMessageAreaView this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.this$0.isAttachedToWindow()) {
            this.this$0.mShrinkAnimator.start();
        }
    }
}
