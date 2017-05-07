// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.AdListener;

public final class at
{
    private AdListener lF;
    private AppEventListener lV;
    private AdSize[] lW;
    private String lX;
    private final bp ml;
    private final aj mm;
    private ap mn;
    private ViewGroup mo;
    private InAppPurchaseListener mp;
    
    public at(final ViewGroup viewGroup) {
        this(viewGroup, null, false, aj.az());
    }
    
    public at(final ViewGroup viewGroup, final AttributeSet set, final boolean b) {
        this(viewGroup, set, b, aj.az());
    }
    
    at(final ViewGroup mo, final AttributeSet set, final boolean b, aj context) {
        this.ml = new bp();
        this.mo = mo;
        this.mm = context;
        if (set == null) {
            return;
        }
        context = (aj)mo.getContext();
        try {
            final an an = new an((Context)context, set);
            this.lW = an.e(b);
            this.lX = an.getAdUnitId();
            if (mo.isInEditMode()) {
                dv.a(mo, new ak((Context)context, this.lW[0]), "Ads by Google");
            }
        }
        catch (IllegalArgumentException ex) {
            dv.a(mo, new ak((Context)context, AdSize.BANNER), ex.getMessage(), ex.getMessage());
        }
    }
    
    private void aF() {
        try {
            final d q = this.mn.Q();
            if (q == null) {
                return;
            }
            this.mo.addView((View)e.d(q));
        }
        catch (RemoteException ex) {
            dw.c("Failed to get an ad frame.", (Throwable)ex);
        }
    }
    
    private void aG() throws RemoteException {
        if ((this.lW == null || this.lX == null) && this.mn == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        final Context context = this.mo.getContext();
        this.mn = ag.a(context, new ak(context, this.lW), this.lX, this.ml);
        if (this.lF != null) {
            this.mn.a(new af(this.lF));
        }
        if (this.lV != null) {
            this.mn.a(new am(this.lV));
        }
        if (this.mp != null) {
            this.mn.a(new cp(this.mp));
        }
        this.aF();
    }
    
    public void a(final as as) {
        try {
            if (this.mn == null) {
                this.aG();
            }
            if (this.mn.a(this.mm.a(this.mo.getContext(), as))) {
                this.ml.c(as.aC());
                this.ml.d(as.aD());
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public void a(final AdSize... lw) {
        this.lW = lw;
        while (true) {
            try {
                if (this.mn != null) {
                    this.mn.a(new ak(this.mo.getContext(), this.lW));
                }
                this.mo.requestLayout();
            }
            catch (RemoteException ex) {
                dw.c("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void destroy() {
        try {
            if (this.mn != null) {
                this.mn.destroy();
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to destroy AdView.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.lF;
    }
    
    public AdSize getAdSize() {
        try {
            if (this.mn != null) {
                return this.mn.R().aA();
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to get the current AdSize.", (Throwable)ex);
        }
        if (this.lW != null) {
            return this.lW[0];
        }
        return null;
    }
    
    public AdSize[] getAdSizes() {
        return this.lW;
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
    
    public void pause() {
        try {
            if (this.mn != null) {
                this.mn.pause();
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to call pause.", (Throwable)ex);
        }
    }
    
    public void recordManualImpression() {
        try {
            this.mn.ac();
        }
        catch (RemoteException ex) {
            dw.c("Failed to record impression.", (Throwable)ex);
        }
    }
    
    public void resume() {
        try {
            if (this.mn != null) {
                this.mn.resume();
            }
        }
        catch (RemoteException ex) {
            dw.c("Failed to call resume.", (Throwable)ex);
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
    
    public void setAdSizes(final AdSize... array) {
        if (this.lW != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.a(array);
    }
    
    public void setAdUnitId(final String lx) {
        if (this.lX != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
}
