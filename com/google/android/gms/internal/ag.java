// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.b;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.ads.AdListener;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class ag
{
    private AppEventListener eI;
    private AdSize[] eJ;
    private String eK;
    private final ba eW;
    private ac eX;
    private ViewGroup eY;
    private AdListener ev;
    
    public ag(final ViewGroup ey) {
        this.eW = new ba();
        this.eY = ey;
    }
    
    public ag(final ViewGroup ey, final AttributeSet set, final boolean b) {
        this.eW = new ba();
        this.eY = ey;
        final Context context = ey.getContext();
        try {
            final aa aa = new aa(context, set);
            this.eJ = aa.c(b);
            this.eK = aa.getAdUnitId();
            if (ey.isInEditMode()) {
                cs.a(ey, new x(context, this.eJ[0]), "Ads by Google");
            }
        }
        catch (IllegalArgumentException ex) {
            cs.a(ey, new x(context, AdSize.BANNER), ex.getMessage(), ex.getMessage());
        }
    }
    
    private void T() {
        try {
            final b x = this.eX.x();
            if (x == null) {
                return;
            }
            this.eY.addView((View)c.b(x));
        }
        catch (RemoteException ex) {
            ct.b("Failed to get an ad frame.", (Throwable)ex);
        }
    }
    
    private void U() throws RemoteException {
        if ((this.eJ == null || this.eK == null) && this.eX == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        final Context context = this.eY.getContext();
        this.eX = u.a(context, new x(context, this.eJ), this.eK, this.eW);
        if (this.ev != null) {
            this.eX.a(new t(this.ev));
        }
        if (this.eI != null) {
            this.eX.a(new z(this.eI));
        }
        this.T();
    }
    
    public void a(final af af) {
        try {
            if (this.eX == null) {
                this.U();
            }
            if (this.eX.a(new v(this.eY.getContext(), af))) {
                this.eW.c(af.R());
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public void a(final AdSize... ej) {
        this.eJ = ej;
        while (true) {
            try {
                if (this.eX != null) {
                    this.eX.a(new x(this.eY.getContext(), this.eJ));
                }
                this.eY.requestLayout();
            }
            catch (RemoteException ex) {
                ct.b("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void destroy() {
        try {
            if (this.eX != null) {
                this.eX.destroy();
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to destroy AdView.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.ev;
    }
    
    public AdSize getAdSize() {
        try {
            if (this.eX != null) {
                return this.eX.y().P();
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to get the current AdSize.", (Throwable)ex);
        }
        if (this.eJ != null) {
            return this.eJ[0];
        }
        return null;
    }
    
    public AdSize[] getAdSizes() {
        return this.eJ;
    }
    
    public String getAdUnitId() {
        return this.eK;
    }
    
    public AppEventListener getAppEventListener() {
        return this.eI;
    }
    
    public void pause() {
        try {
            if (this.eX != null) {
                this.eX.pause();
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to call pause.", (Throwable)ex);
        }
    }
    
    public void recordManualImpression() {
        try {
            this.eX.H();
        }
        catch (RemoteException ex) {
            ct.b("Failed to record impression.", (Throwable)ex);
        }
    }
    
    public void resume() {
        try {
            if (this.eX != null) {
                this.eX.resume();
            }
        }
        catch (RemoteException ex) {
            ct.b("Failed to call resume.", (Throwable)ex);
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
    
    public void setAdSizes(final AdSize... array) {
        if (this.eJ != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.a(array);
    }
    
    public void setAdUnitId(final String ek) {
        if (this.eK != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
}
