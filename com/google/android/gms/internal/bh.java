// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.dynamic.d;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.b;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;

public final class bh
{
    private AdListener nR;
    private String oA;
    private ViewGroup oB;
    private InAppPurchaseListener oC;
    private PlayStorePurchaseListener oD;
    private b oE;
    private AppEventListener oi;
    private AdSize[] oj;
    private String ok;
    private final cs ox;
    private final ax oy;
    private bd oz;
    
    public bh(final ViewGroup viewGroup) {
        this(viewGroup, null, false, ax.bb());
    }
    
    public bh(final ViewGroup viewGroup, final AttributeSet set, final boolean b) {
        this(viewGroup, set, b, ax.bb());
    }
    
    bh(final ViewGroup viewGroup, final AttributeSet set, final boolean b, final ax ax) {
        this(viewGroup, set, b, ax, null);
    }
    
    bh(final ViewGroup ob, final AttributeSet set, final boolean b, ax context, final bd oz) {
        this.ox = new cs();
        this.oB = ob;
        this.oy = context;
        if (set != null) {
            context = (ax)ob.getContext();
            try {
                final bb bb = new bb((Context)context, set);
                this.oj = bb.f(b);
                this.ok = bb.getAdUnitId();
                if (ob.isInEditMode()) {
                    gr.a(ob, new ay((Context)context, this.oj[0]), "Ads by Google");
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                gr.a(ob, new ay((Context)context, AdSize.BANNER), ex.getMessage(), ex.getMessage());
                return;
            }
        }
        this.oz = oz;
    }
    
    private void bh() {
        try {
            final d x = this.oz.X();
            if (x == null) {
                return;
            }
            this.oB.addView((View)e.f(x));
        }
        catch (RemoteException ex) {
            gs.d("Failed to get an ad frame.", (Throwable)ex);
        }
    }
    
    private void bi() throws RemoteException {
        if ((this.oj == null || this.ok == null) && this.oz == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        final Context context = this.oB.getContext();
        this.oz = au.a(context, new ay(context, this.oj), this.ok, this.ox);
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
        if (this.oE != null) {
            this.oz.a(new ew(this.oE, (PublisherAdView)this.oB));
        }
        this.bh();
    }
    
    public void a(final bg bg) {
        try {
            if (this.oz == null) {
                this.bi();
            }
            if (this.oz.a(this.oy.a(this.oB.getContext(), bg))) {
                this.ox.d(bg.be());
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public void a(final AdSize... oj) {
        this.oj = oj;
        while (true) {
            try {
                if (this.oz != null) {
                    this.oz.a(new ay(this.oB.getContext(), this.oj));
                }
                this.oB.requestLayout();
            }
            catch (RemoteException ex) {
                gs.d("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void destroy() {
        try {
            if (this.oz != null) {
                this.oz.destroy();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to destroy AdView.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.nR;
    }
    
    public AdSize getAdSize() {
        try {
            if (this.oz != null) {
                return this.oz.Y().bc();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to get the current AdSize.", (Throwable)ex);
        }
        if (this.oj != null) {
            return this.oj[0];
        }
        return null;
    }
    
    public AdSize[] getAdSizes() {
        return this.oj;
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
    
    public void pause() {
        try {
            if (this.oz != null) {
                this.oz.pause();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to call pause.", (Throwable)ex);
        }
    }
    
    public void recordManualImpression() {
        try {
            if (this.oz != null) {
                this.oz.aj();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to record impression.", (Throwable)ex);
        }
    }
    
    public void resume() {
        try {
            if (this.oz != null) {
                this.oz.resume();
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to call resume.", (Throwable)ex);
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
    
    public void setAdSizes(final AdSize... array) {
        if (this.oj != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.a(array);
    }
    
    public void setAdUnitId(final String ok) {
        if (this.ok != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener od, final String oa) {
        if (this.oC != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
        }
        try {
            this.oD = od;
            this.oA = oa;
            if (this.oz != null) {
                final bd oz = this.oz;
                eq eq;
                if (od != null) {
                    eq = new eq(od);
                }
                else {
                    eq = null;
                }
                oz.a(eq, oa);
            }
        }
        catch (RemoteException ex) {
            gs.d("Failed to set the play store purchase parameter.", (Throwable)ex);
        }
    }
}
