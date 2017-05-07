// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;

@ez
public class dg
{
    private final Context mContext;
    private final WindowManager mG;
    private final gv md;
    private final bl rg;
    DisplayMetrics rh;
    private float ri;
    int rj;
    int rk;
    private int rl;
    private int rm;
    private int rn;
    private int[] ro;
    
    public dg(final gv md, final Context mContext, final bl rg) {
        this.rj = -1;
        this.rk = -1;
        this.rm = -1;
        this.rn = -1;
        this.ro = new int[2];
        this.md = md;
        this.mContext = mContext;
        this.rg = rg;
        this.mG = (WindowManager)mContext.getSystemService("window");
        this.bN();
        this.bO();
        this.bP();
    }
    
    private void bN() {
        this.rh = new DisplayMetrics();
        final Display defaultDisplay = this.mG.getDefaultDisplay();
        defaultDisplay.getMetrics(this.rh);
        this.ri = this.rh.density;
        this.rl = defaultDisplay.getRotation();
    }
    
    private void bP() {
        this.md.getLocationOnScreen(this.ro);
        this.md.measure(0, 0);
        final float n = 160.0f / this.rh.densityDpi;
        this.rm = Math.round(this.md.getMeasuredWidth() * n);
        this.rn = Math.round(n * this.md.getMeasuredHeight());
    }
    
    private df bV() {
        return new df.a().j(this.rg.bj()).i(this.rg.bk()).k(this.rg.bo()).l(this.rg.bl()).m(this.rg.bm()).bM();
    }
    
    void bO() {
        final int s = gj.s(this.mContext);
        final float n = 160.0f / this.rh.densityDpi;
        this.rj = Math.round(this.rh.widthPixels * n);
        this.rk = Math.round((this.rh.heightPixels - s) * n);
    }
    
    public void bQ() {
        this.bT();
        this.bU();
        this.bS();
        this.bR();
    }
    
    public void bR() {
        if (gs.u(2)) {
            gs.U("Dispatching Ready Event.");
        }
        this.md.b("onReadyEventReceived", new JSONObject());
    }
    
    public void bS() {
        try {
            this.md.b("onDefaultPositionReceived", new JSONObject().put("x", this.ro[0]).put("y", this.ro[1]).put("width", this.rm).put("height", this.rn));
        }
        catch (JSONException ex) {
            gs.b("Error occured while dispatching default position.", (Throwable)ex);
        }
    }
    
    public void bT() {
        try {
            this.md.b("onScreenInfoChanged", new JSONObject().put("width", this.rj).put("height", this.rk).put("density", (double)this.ri).put("rotation", this.rl));
        }
        catch (JSONException ex) {
            gs.b("Error occured while obtaining screen information.", (Throwable)ex);
        }
    }
    
    public void bU() {
        this.md.b("onDeviceFeaturesReceived", this.bV().bL());
    }
}
