// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.c;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.AdListener;
import android.content.Context;

public class bi
{
    private final Context mContext;
    private AdListener nR;
    private String oA;
    private InAppPurchaseListener oC;
    private PlayStorePurchaseListener oD;
    private PublisherInterstitialAd oF;
    private c oG;
    private AppEventListener oi;
    private String ok;
    private final cs ox;
    private final ax oy;
    private bd oz;
    
    public bi(final Context context) {
        this(context, ax.bb(), null);
    }
    
    public bi(final Context context, final PublisherInterstitialAd publisherInterstitialAd) {
        this(context, ax.bb(), publisherInterstitialAd);
    }
    
    public bi(final Context mContext, final ax oy, final PublisherInterstitialAd of) {
        this.ox = new cs();
        this.mContext = mContext;
        this.oy = oy;
        this.oF = of;
    }
    
    private void v(final String s) {
        if (this.ok == null) {
            this.w(s);
        }
        this.oz = au.a(this.mContext, new ay(), this.ok, this.ox);
        if (this.nR != null) {
            this.oz.a(new at(this.nR));
        }
        if (this.oi != null) {
            this.oz.a(new ba(this.oi));
        }
        if (this.oC != null) {
            this.oz.a(new em(this.oC));
        }
        if (this.oD != null) {
            this.oz.a(new eq(this.oD), this.oA);
        }
        if (this.oG != null) {
            this.oz.a(new ex(this.oG, this.oF));
        }
    }
    
    private void w(final String s) {
        if (this.oz == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + s + " is called.");
        }
    }
    
    public void a(final bg bg) {
        try {
            if (this.oz == null) {
                this.v("loadAd");
            }
            if (this.oz.a(this.oy.a(this.mContext, bg))) {
                this.ox.d(bg.be());
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.nR;
    }
    
    public String getAdUnitId() {
        return this.ok;
    }
    
    public AppEventListener getAppEventListener() {
        return this.oi;
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.oC;
    }
    
    public String getMediationAdapterClassName() {
        try {
            if (this.oz != null) {
                return this.oz.getMediationAdapterClassName();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to get the mediation adapter class name.", (Throwable)ex);
        }
        return null;
    }
    
    public boolean isLoaded() {
        try {
            return this.oz != null && this.oz.isReady();
        }
        catch (RemoteException ex) {
            gs.d("Failed to check if ad is ready.", (Throwable)ex);
            return false;
        }
    }
    
    public void setAdListener(final AdListener nr) {
        try {
            this.nR = nr;
            if (this.oz != null) {
                final bd oz = this.oz;
                at at;
                if (nr != null) {
                    at = new at(nr);
                }
                else {
                    at = null;
                }
                oz.a(at);
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void setAdUnitId(final String ok) {
        if (this.ok != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.ok = ok;
    }
    
    public void setAppEventListener(final AppEventListener oi) {
        try {
            this.oi = oi;
            if (this.oz != null) {
                final bd oz = this.oz;
                ba ba;
                if (oi != null) {
                    ba = new ba(oi);
                }
                else {
                    ba = null;
                }
                oz.a(ba);
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to set the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener oc) {
        if (this.oD != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.oC = oc;
            if (this.oz != null) {
                final bd oz = this.oz;
                em em;
                if (oc != null) {
                    em = new em(oc);
                }
                else {
                    em = null;
                }
                oz.a(em);
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener od, final String s) {
        try {
            this.oD = od;
            if (this.oz != null) {
                final bd oz = this.oz;
                eq eq;
                if (od != null) {
                    eq = new eq(od);
                }
                else {
                    eq = null;
                }
                oz.a(eq, s);
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to set the play store purchase parameter.", (Throwable)ex);
        }
    }
    
    public void show() {
        try {
            this.w("show");
            this.oz.showInterstitial();
        }
        catch (RemoteException ex) {
            gs.d("Failed to show interstitial.", (Throwable)ex);
        }
    }
}
