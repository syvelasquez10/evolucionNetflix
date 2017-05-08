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

public class UserMessageAreaView extends LinearLayout
{
    private static final String TAG = "UserMessageAreaView";
    private final View mAlertView;
    private final ViewPropertyAnimator mAlphaAnimation;
    private final TextView mBody;
    private final ViewGroup mCtaContainer;
    private final Space mDimSpace;
    private final IconFontTextView mIcon;
    private ListView mParentView;
    private BroadcastReceiver mReceiver;
    private final ValueAnimator mShrinkAnimator;
    private Space mSpaceAsHeader;
    private final TextView mTitle;
    private UmaAlert mUmaAlert;
    
    public UserMessageAreaView(final Context context) {
        super(context);
        this.mReceiver = new UserMessageAreaView$1(this);
        inflate(context, 2130903307, (ViewGroup)this);
        (this.mTitle = (TextView)this.findViewById(2131689574)).setMovementMethod(LinkMovementMethod.getInstance());
        (this.mBody = (TextView)this.findViewById(2131690377)).setMovementMethod(LinkMovementMethod.getInstance());
        this.mCtaContainer = (ViewGroup)this.findViewById(2131690378);
        this.mIcon = (IconFontTextView)this.findViewById(2131689573);
        this.mAlertView = this.findViewById(2131690375);
        this.mDimSpace = (Space)this.findViewById(2131690376);
        this.setOnTouchListener((View$OnTouchListener)new UserMessageAreaView$2(this));
        LocalizationUtils.setLayoutDirection((View)this.mCtaContainer);
        (this.mAlphaAnimation = this.animate()).setDuration((long)this.getResources().getInteger(17694721));
        this.mAlphaAnimation.setListener((Animator$AnimatorListener)new UserMessageAreaView$3(this));
        (this.mShrinkAnimator = new ValueAnimator()).setStartDelay(20L);
        this.mShrinkAnimator.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        this.mShrinkAnimator.setFloatValues(new float[] { 0.0f, 1.0f });
        this.mShrinkAnimator.setDuration((long)this.getResources().getInteger(17694721));
        this.mShrinkAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new UserMessageAreaView$4(this));
        this.mShrinkAnimator.addListener((Animator$AnimatorListener)new UserMessageAreaView$5(this));
        this.setVisibility(8);
    }
    
    private void addCta(final UmaCta umaCta) {
        final Context context = this.getContext();
        int n;
        if (umaCta.selected()) {
            n = 2131427777;
        }
        else {
            n = 2131427776;
        }
        final NetflixTextButton netflixTextButton = new NetflixTextButton((Context)new ContextThemeWrapper(context, n));
        netflixTextButton.setText((CharSequence)umaCta.text());
        this.mCtaContainer.addView((View)netflixTextButton, new ViewGroup$LayoutParams(-2, -2));
        this.mCtaContainer.setVisibility(0);
        this.mCtaContainer.requestLayout();
        if (TextUtils.equals((CharSequence)"LINK", (CharSequence)umaCta.actionType())) {
            netflixTextButton.setOnClickListener((View$OnClickListener)new UserMessageAreaView$7(this, umaCta));
        }
        else if (TextUtils.equals((CharSequence)"UMS_IMPRESSION", (CharSequence)umaCta.actionType()) && TextUtils.equals((CharSequence)"NOT_NOW", (CharSequence)umaCta.action())) {
            netflixTextButton.setOnClickListener((View$OnClickListener)new UserMessageAreaView$8(this, umaCta));
        }
    }
    
    private void bind() {
        final CharSequence charSequence = null;
        final TextView mTitle = this.mTitle;
        Object fromHtml;
        if (this.mUmaAlert.title() == null) {
            fromHtml = null;
        }
        else {
            fromHtml = Html.fromHtml(this.mUmaAlert.title());
        }
        mTitle.setText((CharSequence)fromHtml);
        final TextView mBody = this.mBody;
        Object fromHtml2;
        if (this.mUmaAlert.body() == null) {
            fromHtml2 = charSequence;
        }
        else {
            fromHtml2 = Html.fromHtml(this.mUmaAlert.body());
        }
        mBody.setText((CharSequence)fromHtml2);
        this.mCtaContainer.removeAllViews();
        final UmaCta cta1 = this.mUmaAlert.cta1();
        if (cta1 != null) {
            this.addCta(cta1);
        }
        final UmaCta cta2 = this.mUmaAlert.cta2();
        if (cta2 != null) {
            this.addCta(cta2);
        }
        if ("INFO".equalsIgnoreCase(this.mUmaAlert.viewType())) {
            this.mIcon.setVisibility(0);
            this.mIcon.setText(2131231477);
            this.mIcon.setTextColor(this.getResources().getColor(2131624052));
            return;
        }
        if ("WARN".equalsIgnoreCase(this.mUmaAlert.viewType())) {
            this.mIcon.setVisibility(0);
            this.mIcon.setText(2131231489);
            this.mIcon.setTextColor(this.getResources().getColor(2131624053));
            return;
        }
        if ("ERROR".equalsIgnoreCase(this.mUmaAlert.viewType())) {
            this.mIcon.setVisibility(0);
            this.mIcon.setText(2131231474);
            this.mIcon.setTextColor(this.getResources().getColor(2131624054));
            return;
        }
        this.mIcon.setVisibility(8);
    }
    
    private void openLinkWithAutoLoginToken(final NetflixActivity netflixActivity, final UmaCta umaCta) {
        if (netflixActivity.getServiceManager() == null || !netflixActivity.getServiceManager().isReady()) {
            ErrorLoggingManager.logHandledException("Unable to generate token, no service. [serviceManager:" + netflixActivity.getServiceManager() + "]");
            return;
        }
        final AccountHandler accountHandler = new AccountHandler(netflixActivity);
        final UserMessageAreaView$9 userMessageAreaView$9 = new UserMessageAreaView$9(this, accountHandler, new NetworkErrorStatus(VolleyUtils.TIMEOUT_ERROR), umaCta);
        netflixActivity.getHandler().postDelayed((Runnable)userMessageAreaView$9, 10000L);
        netflixActivity.getServiceManager().createAutoLoginToken(3600000L, new UserMessageAreaView$10(this, userMessageAreaView$9, accountHandler, umaCta));
    }
    
    public void dismiss(final boolean b) {
        if (this.mUmaAlert != null) {
            if (b) {
                this.mAlphaAnimation.alpha(0.0f);
            }
            else {
                if (this.mUmaAlert.blocking()) {
                    final ViewParent parent = this.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup)parent).removeView((View)this);
                    }
                    this.mParentView.removeHeaderView((View)this.mSpaceAsHeader);
                    return;
                }
                this.mParentView.removeHeaderView((View)this);
            }
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mReceiver, new IntentFilter("RefreshUserMessageRequest.ACTION_UMA_MESSAGE_UPDATED"));
    }
    
    protected void onDetachedFromWindow() {
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mReceiver);
        super.onDetachedFromWindow();
    }
    
    public void show(final UmaAlert mUmaAlert, final ListView mParentView, final ViewGroup viewGroup) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.getContext(), NetflixActivity.class);
        if (netflixActivity != null) {
            this.mParentView = mParentView;
            this.mUmaAlert = mUmaAlert;
            this.bind();
            this.setVisibility(0);
            if (this.mUmaAlert.blocking()) {
                this.setPadding(0, netflixActivity.getActionBarHeight(), 0, 0);
                viewGroup.addView((View)this, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
                this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new UserMessageAreaView$6(this));
                this.mDimSpace.setVisibility(0);
                this.setBackgroundResource(2131624175);
            }
            else {
                this.mParentView.addHeaderView((View)this, (Object)null, false);
                this.mDimSpace.setVisibility(8);
                this.setBackground((Drawable)null);
            }
            Log.e("UserMessageAreaView", "Displaying uma alert created on " + new Date(this.mUmaAlert.timestamp()));
        }
    }
}
