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
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.View$OnTouchListener;
import android.text.method.LinkMovementMethod;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.animation.ValueAnimator;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.Space;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewPropertyAnimator;
import android.view.View;
import android.widget.LinearLayout;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class UserMessageAreaView$1 extends BroadcastReceiver
{
    final /* synthetic */ UserMessageAreaView this$0;
    
    UserMessageAreaView$1(final UserMessageAreaView this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.this$0.getContext(), NetflixActivity.class);
        if (netflixActivity != null && netflixActivity.getServiceManager() != null && netflixActivity.getServiceManager().isReady()) {
            this.this$0.mUmaAlert = netflixActivity.getServiceManager().getUserMessageAlert();
            if (this.this$0.mUmaAlert == null) {
                this.this$0.dismiss(true);
            }
        }
    }
}
