// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.ums;

import com.netflix.mediaclient.Log;
import java.util.Date;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.FrameLayout$LayoutParams;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.ViewParent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetworkErrorStatus;
import com.netflix.mediaclient.util.VolleyUtils;
import com.netflix.mediaclient.ui.home.AccountHandler;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixTextButton;
import android.view.ContextThemeWrapper;
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
import android.widget.LinearLayout;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaCta;
import android.view.View$OnClickListener;

class UserMessageAreaView$8 implements View$OnClickListener
{
    final /* synthetic */ UserMessageAreaView this$0;
    final /* synthetic */ UmaCta val$umaCta;
    
    UserMessageAreaView$8(final UserMessageAreaView this$0, final UmaCta val$umaCta) {
        this.this$0 = this$0;
        this.val$umaCta = val$umaCta;
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(view.getContext(), NetflixActivity.class);
        if (netflixActivity != null && !AndroidUtils.isActivityFinishedOrDestroyed(netflixActivity)) {
            netflixActivity.getServiceManager().recordUserMessageImpression(this.this$0.mUmaAlert.messageName(), this.val$umaCta.callback());
            netflixActivity.getServiceManager().consumeUmaAlert();
            this.this$0.dismiss(true);
        }
        else if (view.getContext() != null) {
            throw new IllegalArgumentException("Expected to UMA view to run in a NetflixActivity, found " + this.this$0.getContext());
        }
    }
}
