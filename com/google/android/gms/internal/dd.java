// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.ViewParent;
import com.google.android.gms.ads.AdSize;
import android.app.Activity;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Map;
import android.content.Context;
import java.util.Set;

@ez
public class dd
{
    static final Set<String> qT;
    private int lf;
    private int lg;
    private final Context mContext;
    private final gv md;
    private final Map<String, String> qM;
    private int qU;
    private int qV;
    private boolean qW;
    private String qX;
    
    static {
        qT = new HashSet<String>(Arrays.asList("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"));
    }
    
    public dd(final gv md, final Map<String, String> qm) {
        this.lf = -1;
        this.lg = -1;
        this.qU = 0;
        this.qV = 0;
        this.qW = true;
        this.qX = "top-right";
        this.md = md;
        this.qM = qm;
        this.mContext = md.dA();
    }
    
    private void bG() {
        final int[] t = gj.t(this.mContext);
        if (!TextUtils.isEmpty((CharSequence)this.qM.get("width"))) {
            final int m = gj.M(this.qM.get("width"));
            if (this.b(m, t[0])) {
                this.lf = m;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.qM.get("height"))) {
            final int i = gj.M(this.qM.get("height"));
            if (this.c(i, t[1])) {
                this.lg = i;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.qM.get("offsetX"))) {
            this.qU = gj.M(this.qM.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence)this.qM.get("offsetY"))) {
            this.qV = gj.M(this.qM.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence)this.qM.get("allowOffscreen"))) {
            this.qW = Boolean.parseBoolean(this.qM.get("allowOffscreen"));
        }
        final String qx = this.qM.get("customClosePosition");
        if (!TextUtils.isEmpty((CharSequence)qx) && dd.qT.contains(qx)) {
            this.qX = qx;
        }
    }
    
    boolean b(final int n, final int n2) {
        return n >= 50 && n < n2;
    }
    
    boolean bI() {
        return this.lf > -1 && this.lg > -1;
    }
    
    void bJ() {
        try {
            this.md.b("onSizeChanged", new JSONObject().put("x", this.qU).put("y", this.qV).put("width", this.lf).put("height", this.lg));
        }
        catch (JSONException ex) {
            gs.b("Error occured while dispatching size change.", (Throwable)ex);
        }
    }
    
    void bK() {
        try {
            this.md.b("onStateChanged", new JSONObject().put("state", (Object)"resized"));
        }
        catch (JSONException ex) {
            gs.b("Error occured while dispatching state change.", (Throwable)ex);
        }
    }
    
    boolean c(final int n, final int n2) {
        return n >= 50 && n < n2;
    }
    
    public void execute() {
        gs.U("PLEASE IMPLEMENT mraid.resize()");
        if (this.mContext == null) {
            gs.W("Not an activity context. Cannot resize.");
            return;
        }
        if (this.md.Y().og) {
            gs.W("Is interstitial. Cannot resize an interstitial.");
            return;
        }
        if (this.md.dz()) {
            gs.W("Is expanded. Cannot resize an expanded banner.");
            return;
        }
        this.bG();
        if (!this.bI()) {
            gs.W("Invalid width and height options. Cannot resize.");
            return;
        }
        final WindowManager windowManager = (WindowManager)this.mContext.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int a = gr.a(displayMetrics, this.lf);
        final int a2 = gr.a(displayMetrics, this.lg);
        final ViewParent parent = this.md.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this.md);
        }
        final LinearLayout contentView = new LinearLayout(this.mContext);
        contentView.setBackgroundColor(0);
        final PopupWindow popupWindow = new PopupWindow(this.mContext);
        popupWindow.setHeight(a2 + 16);
        popupWindow.setWidth(a + 16);
        popupWindow.setClippingEnabled(!this.qW);
        popupWindow.setContentView((View)contentView);
        contentView.addView((View)this.md, -1, -1);
        popupWindow.showAtLocation(((Activity)this.mContext).getWindow().getDecorView(), 0, this.qU, this.qV);
        this.md.a(new ay(this.mContext, new AdSize(this.lf, this.lg)));
        this.bJ();
        this.bK();
    }
}
