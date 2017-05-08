// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import android.os.SystemClock;
import android.app.Application$ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow$OnDismissListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.Queue;

public abstract class Advisor
{
    public static final long DEFAULT_DELAY;
    public static final long DEFAULT_DURATION;
    public static final long SLOP = 15000L;
    public static final String TAG = "AdvisorToast";
    private static Queue<Advisor> sQueue;
    Advisory advisory;
    View anchor;
    NetflixActivity context;
    float delayInSeconds;
    PopupWindow$OnDismissListener dismissListener;
    private final Runnable dismissPopup;
    float durationInSeconds;
    protected boolean hasSecondMessage;
    private PopupWindow$OnDismissListener internalDismiss;
    boolean isHardDismiss;
    TextView messageView;
    final View popupView;
    final PopupWindow popupWindow;
    TextView secondaryMessageView;
    private final Runnable showPopup;
    long timeSinceLastShow;
    
    static {
        DEFAULT_DURATION = TimeUnit.SECONDS.toSeconds(4L);
        DEFAULT_DELAY = TimeUnit.SECONDS.toSeconds(0L);
        Advisor.sQueue = new LinkedList<Advisor>();
    }
    
    protected Advisor(final NetflixActivity context, final Advisory advisory) {
        this.durationInSeconds = Advisor.DEFAULT_DURATION;
        this.delayInSeconds = Advisor.DEFAULT_DELAY;
        this.timeSinceLastShow = 0L;
        this.isHardDismiss = false;
        this.showPopup = new Advisor$1(this);
        this.dismissPopup = new Advisor$2(this);
        this.internalDismiss = (PopupWindow$OnDismissListener)new Advisor$3(this);
        this.context = context;
        this.advisory = advisory;
        this.popupView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(this.getLayoutId(), (ViewGroup)null);
        (this.popupWindow = new PopupWindow(this.popupView, -1, -2)).setOutsideTouchable(false);
        this.popupWindow.setBackgroundDrawable((Drawable)null);
        this.popupWindow.setOnDismissListener(this.internalDismiss);
        this.popupWindow.setInputMethodMode(2);
        this.messageView = (TextView)this.popupView.findViewById(this.getMessageViewId());
        this.secondaryMessageView = (TextView)this.popupView.findViewById(this.getSecondaryMessageViewId());
        final View viewById = this.popupView.findViewById(this.getMessageViewId());
        if (viewById != null) {
            viewById.setVisibility(4);
        }
        final View viewById2 = this.popupView.findViewById(this.getSecondaryMessageViewId());
        if (viewById2 != null) {
            viewById2.setVisibility(4);
        }
        if (advisory != null) {
            this.withMessage(advisory.getMessage((Context)context)).withSecondaryMessage(advisory.getSecondaryMessage((Context)context)).withDelay(advisory.getDelay()).forDuration(advisory.getDuration());
        }
    }
    
    public static void destroyAll() {
        dismissAll();
        Advisor.sQueue.clear();
    }
    
    public static void dismissAll() {
        if (!Advisor.sQueue.isEmpty()) {
            Advisor.sQueue.peek().dismissHard();
        }
    }
    
    private void dismissWindow() {
        if (this.popupWindow.isShowing()) {
            this.popupWindow.dismiss();
            this.context.setLifeCycleListener(null);
        }
    }
    
    public static int getQueueSize() {
        return Advisor.sQueue.size();
    }
    
    private static boolean hasShownLongEnough(final Advisor advisor) {
        if (advisor != null && advisor.getAdvisory() != null) {
            final long n = (long)(advisor.getAdvisory().getDuration() * TimeUnit.SECONDS.toMillis(1L) + advisor.timeSinceLastShow);
            final long uptimeMillis = SystemClock.uptimeMillis();
            if (Log.isLoggable()) {
                Log.d("AdvisorToast", "hasShown? " + n + " - " + uptimeMillis + " = " + (n - uptimeMillis));
            }
            if (n - uptimeMillis >= 0L) {
                return false;
            }
        }
        return true;
    }
    
    public static <T extends Advisor> T make(final NetflixActivity netflixActivity, final Advisory advisory) {
        switch (Advisor$5.$SwitchMap$com$netflix$model$leafs$advisory$Advisory$Type[advisory.getType().ordinal()]) {
            default: {
                return (T)new ProductPlacementAdvisor(netflixActivity, advisory);
            }
            case 1: {
                return (T)new ContentAdvisor(netflixActivity, advisory);
            }
            case 2: {
                return (T)new ExpiringContentAdvisor(netflixActivity, advisory);
            }
        }
    }
    
    private static void processNextInQueue(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("AdvisorToast", "processNextInQueue .... stopAfterRemoval -> " + b + " .... size -> " + Advisor.sQueue.size());
        }
        if (!Advisor.sQueue.isEmpty()) {
            if (!hasShownLongEnough(Advisor.sQueue.peek())) {
                return;
            }
            Log.d("AdvisorToast", "hasShownEnough...");
            Advisor.sQueue.remove();
        }
        if (!b && !Advisor.sQueue.isEmpty()) {
            final Advisor advisor = Advisor.sQueue.peek();
            if (advisor == null) {
                Advisor.sQueue.remove();
                processNextInQueue(false);
                return;
            }
            advisor.showInternal();
        }
    }
    
    private void queueIfDoesntExist(final boolean b) {
        if (!Advisor.sQueue.contains(this)) {
            Advisor.sQueue.offer(this);
        }
        if (Advisor.sQueue.size() == 1 && b) {
            this.showInternal();
        }
    }
    
    public static void showAll() {
        if (!Advisor.sQueue.isEmpty()) {
            Advisor.sQueue.peek().showInternal();
        }
    }
    
    public Advisor anchoredAt(final View anchor) {
        this.anchor = anchor;
        return this;
    }
    
    public void dismiss() {
        this.dismissWindow();
        if (this.dismissListener != null) {
            this.dismissListener.onDismiss();
        }
    }
    
    public void dismissHard() {
        this.isHardDismiss = true;
        this.dismissWindow();
    }
    
    public Advisor forDuration(final float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }
    
    public Advisory getAdvisory() {
        return this.advisory;
    }
    
    public abstract int getLayoutId();
    
    public abstract int getMessageViewId();
    
    protected View getRootView() {
        return this.popupView;
    }
    
    public abstract int getSecondaryMessageViewId();
    
    public boolean isShowing() {
        return this.popupWindow.isShowing();
    }
    
    public void queue() {
        this.queueIfDoesntExist(false);
    }
    
    public void show() {
        this.queueIfDoesntExist(true);
    }
    
    protected void showInternal() {
        this.isHardDismiss = false;
        this.context.getHandler().postDelayed(this.showPopup, (long)this.delayInSeconds * TimeUnit.SECONDS.toMillis(1L));
    }
    
    public Advisor withAnimation(final int animationStyle) {
        this.popupWindow.setAnimationStyle(animationStyle);
        return this;
    }
    
    public Advisor withDelay(final float delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
        return this;
    }
    
    public Advisor withDismissListener(final PopupWindow$OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
        return this;
    }
    
    public Advisor withMessage(final String text) {
        if (this.messageView != null && !TextUtils.isEmpty((CharSequence)text)) {
            this.messageView.setText((CharSequence)text);
            ViewUtils.setVisibleOrGone((View)this.messageView, true);
        }
        return this;
    }
    
    public Advisor withSecondaryMessage(final String text) {
        if (this.secondaryMessageView != null && !TextUtils.isEmpty((CharSequence)text)) {
            this.secondaryMessageView.setText((CharSequence)text);
            ViewUtils.setVisibleOrGone((View)this.secondaryMessageView, true);
            this.hasSecondMessage = true;
        }
        return this;
    }
    
    public Advisor withViewTracking(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new Advisor$4(this, view));
        return this;
    }
}
