// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class ah
{
    private AppEventListener eI;
    private String eK;
    private final ba eW;
    private ac eX;
    private AdListener ev;
    private final Context mContext;
    
    public ah(final Context mContext) {
        this.eW = new ba();
        this.mContext = mContext;
    }
    
    private void j(final String s) throws RemoteException {
        if (this.eK == null) {
            this.k(s);
        }
        this.eX = u.a(this.mContext, new x(), this.eK, this.eW);
        if (this.ev != null) {
            this.eX.a(new t(this.ev));
        }
        if (this.eI != null) {
            this.eX.a(new z(this.eI));
        }
    }
    
    private void k(final String s) {
        if (this.eX == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + s + " is called.");
        }
    }
    
    public void a(final af af) {
        try {
            if (this.eX == null) {
                this.j("loadAd");
            }
            if (this.eX.a(new v(this.mContext, af))) {
                this.eW.c(af.R());
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.ev;
    }
    
    public String getAdUnitId() {
        return this.eK;
    }
    
    public AppEventListener getAppEventListener() {
        return this.eI;
    }
    
    public boolean isLoaded() {
        try {
            return this.eX != null && this.eX.isReady();
        }
        catch (RemoteException ex) {
            ct.b("Failed to check if ad is ready.", (Throwable)ex);
            return false;
        }
    }
    
    public void setAdListener(final AdListener ev) {
        try {
            this.ev = ev;
            if (this.eX != null) {
                final ac ex = this.eX;
                t t;
                if (ev != null) {
                    t = new t(ev);
                }
                else {
                    t = null;
                }
                ex.a(t);
            }
        }
        catch (RemoteException ex2) {
            ct.b("Failed to set the AdListener.", (Throwable)ex2);
        }
    }
    
    public void setAdUnitId(final String ek) {
        if (this.eK != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.eK = ek;
    }
    
    public void setAppEventListener(final AppEventListener ei) {
        try {
            this.eI = ei;
            if (this.eX != null) {
                final ac ex = this.eX;
                z z;
                if (ei != null) {
                    z = new z(ei);
                }
                else {
                    z = null;
                }
                ex.a(z);
            }
        }
        catch (RemoteException ex2) {
            ct.b("Failed to set the AppEventListener.", (Throwable)ex2);
        }
    }
    
    public void show() {
        try {
            this.k("show");
            this.eX.showInterstitial();
        }
        catch (RemoteException ex) {
            ct.b("Failed to show interstitial.", (Throwable)ex);
        }
    }
}
