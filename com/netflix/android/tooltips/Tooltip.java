// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.util.Log;
import android.text.Html;
import android.content.SharedPreferences;
import android.view.View$OnLayoutChangeListener;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

public final class Tooltip
{
    private static final String PREFS = "com.netflix.android.tooltips";
    private static final String PREF_CONSUMED = "consumed.";
    private static final String TAG = "Tooltip";
    private Tooltip$Callback mCallback;
    private String mKey;
    private final CoordinatorLayout mParent;
    private final TooltipLayout mTooltipLayout;
    
    private Tooltip(final Context context, final CoordinatorLayout mParent, final View target, final CharSequence title, final CharSequence detail) {
        this.mParent = mParent;
        (this.mTooltipLayout = (TooltipLayout)LayoutInflater.from(context).inflate(R$layout.tooltip_layout, (ViewGroup)this.mParent, false)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.mTooltipLayout.setOnClickListener((View$OnClickListener)new Tooltip$1(this));
        this.mTooltipLayout.setTitle(title);
        this.mTooltipLayout.setDetail(detail);
        target.addOnLayoutChangeListener((View$OnLayoutChangeListener)new Tooltip$2(this, target));
        this.mTooltipLayout.setTarget(target);
    }
    
    public static void clearPrefs(final Context context) {
        context.getSharedPreferences("com.netflix.android.tooltips", 0).edit().clear().apply();
    }
    
    private String getPrefKey() {
        return "consumed." + this.mKey;
    }
    
    private SharedPreferences getPrefs() {
        return this.mParent.getContext().getSharedPreferences("com.netflix.android.tooltips", 0);
    }
    
    public static boolean isConsumed(final Context context, final String s) {
        return context.getSharedPreferences("com.netflix.android.tooltips", 0).getBoolean("consumed." + s, false);
    }
    
    public static Tooltip makeTooltip(final Context context, final CoordinatorLayout coordinatorLayout, final View view, final int n, final int n2) {
        return new Tooltip(context, coordinatorLayout, view, (CharSequence)Html.fromHtml(context.getResources().getString(n)), (CharSequence)Html.fromHtml(context.getResources().getString(n2)));
    }
    
    public static Tooltip makeTooltip(final Context context, final CoordinatorLayout coordinatorLayout, final View view, final CharSequence charSequence, final CharSequence charSequence2) {
        return new Tooltip(context, coordinatorLayout, view, charSequence, charSequence2);
    }
    
    public void dismiss() {
        if (!this.isConsumed()) {
            this.mTooltipLayout.dismiss(this.mParent);
        }
    }
    
    public View getTarget() {
        return this.mTooltipLayout.getTarget();
    }
    
    public boolean isConsumed() {
        boolean b = false;
        if (this.mKey != null) {
            b = b;
            if (this.getPrefs().getBoolean(this.getPrefKey(), false)) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean isShown() {
        return !this.isConsumed() && this.mTooltipLayout.isShown();
    }
    
    public void setCallback(final Tooltip$Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    public void setOnClickListener(final View$OnClickListener userOnClickListener) {
        this.mTooltipLayout.setUserOnClickListener(userOnClickListener);
    }
    
    public void setOneTime(final String mKey) {
        this.mKey = mKey;
    }
    
    public void show() {
        if (this.mTooltipLayout.isShown()) {
            Log.d("Tooltip", "Tooltip is already showing");
        }
        else if (!this.isConsumed()) {
            this.mTooltipLayout.show(this.mParent);
            this.mTooltipLayout.setCallback(new Tooltip$3(this));
        }
    }
}
