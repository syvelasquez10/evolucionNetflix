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
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.View$OnTouchListener;
import android.text.method.LinkMovementMethod;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.content.BroadcastReceiver;
import android.widget.ListView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.Space;
import android.widget.TextView;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.view.View;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class UserMessageAreaView$4 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ UserMessageAreaView this$0;
    
    UserMessageAreaView$4(final UserMessageAreaView this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        if (this.this$0.isAttachedToWindow()) {
            Object o;
            if (this.this$0.mUmaAlert.blocking()) {
                o = this.this$0.mSpaceAsHeader;
            }
            else {
                o = this.this$0;
            }
            if (valueAnimator.getAnimatedFraction() != 1.0f) {
                ((View)o).getLayoutParams().height = Math.max(1, (int)(((View)o).getMeasuredHeight() - valueAnimator.getAnimatedFraction() * ((View)o).getMeasuredHeight()));
                ((View)o).requestLayout();
                return;
            }
            this.this$0.mParentView.removeHeaderView((View)o);
            if (this.this$0.mUmaAlert.blocking()) {
                ((ViewGroup)this.this$0.getParent()).removeView((View)this.this$0);
            }
        }
    }
}
