// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.content.Context;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.AdListener;

public final class au
{
    private AdListener lF;
    private AppEventListener lV;
    private String lX;
    private final Context mContext;
    private final bp ml;
    private final aj mm;
    private ap mn;
    private InAppPurchaseListener mp;
    
    public au(final Context context) {
        this(context, aj.az());
    }
    
    public au(final Context mContext, final aj mm) {
        this.ml = new bp();
        this.mContext = mContext;
        this.mm = mm;
    }
    
    private void k(final String s) throws RemoteException {
        if (this.lX == null) {
            this.l(s);
        }
        this.mn = ag.a(this.mContext, new ak(), this.lX, this.ml);
        if (this.lF != null) {
            this.mn.a(new af(this.lF));
        }
        if (this.lV != null) {
            this.mn.a(new am(this.lV));
        }
        if (this.mp != null) {
            this.mn.a(new cp(this.mp));
        }
    }
    
    private void l(final String s) {
        if (this.mn == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + s + " is called.");
        }
    }
    
    public void a(final as as) {
        try {
            if (this.mn == null) {
                this.k("loadAd");
            }
            if (this.mn.a(this.mm.a(this.mContext, as))) {
                this.ml.c(as.aC());
                this.ml.d(as.aD());
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.lF;
    }
    
    public String getAdUnitId() {
        return this.lX;
    }
    
    public AppEventListener getAppEventListener() {
        return this.lV;
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.mp;
    }
    
    public boolean isLoaded() {
        try {
            return this.mn != null && this.mn.isReady();
        }
        catch (RemoteException ex) {
            dw.c("Failed to check if ad is ready.", (Throwable)ex);
            return false;
        }
    }
    
    public void setAdListener(final AdListener lf) {
        try {
            this.lF = lf;
            if (this.mn != null) {
                final ap mn = this.mn;
                af af;
                if (lf != null) {
                    af = new af(lf);
                }
                else {
                    af = null;
                }
                mn.a(af);
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void setAdUnitId(final String lx) {
        if (this.lX != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.lX = lx;
    }
    
    public void setAppEventListener(final AppEventListener lv) {
        try {
            this.lV = lv;
            if (this.mn != null) {
                final ap mn = this.mn;
                am am;
                if (lv != null) {
                    am = new am(lv);
                }
                else {
                    am = null;
                }
                mn.a(am);
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to set the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener mp) {
        try {
            this.mp = mp;
            if (this.mn != null) {
                final ap mn = this.mn;
                cp cp;
                if (mp != null) {
                    cp = new cp(mp);
                }
                else {
                    cp = null;
                }
                mn.a(cp);
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void show() {
        try {
            this.l("show");
            this.mn.showInterstitial();
        }
        catch (RemoteException ex) {
            dw.c("Failed to show interstitial.", (Throwable)ex);
        }
    }
}
