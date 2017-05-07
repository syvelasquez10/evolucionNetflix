// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzck;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzfx;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;

public class zzz
{
    private boolean zzoN;
    private String zzpa;
    private zza zzsy;
    private AdListener zzsz;
    private zzs zztF;
    private ViewGroup zztH;
    private InAppPurchaseListener zztI;
    private PlayStorePurchaseListener zztJ;
    private OnCustomRenderedAdLoadedListener zztK;
    private AppEventListener zztj;
    private AdSize[] zztk;
    
    public AdListener getAdListener() {
        return this.zzsz;
    }
    
    public AdSize getAdSize() {
        try {
            if (this.zztF != null) {
                final AdSizeParcel zzaN = this.zztF.zzaN();
                if (zzaN != null) {
                    return zzaN.zzcD();
                }
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get the current AdSize.", (Throwable)ex);
        }
        if (this.zztk != null) {
            return this.zztk[0];
        }
        return null;
    }
    
    public AdSize[] getAdSizes() {
        return this.zztk;
    }
    
    public String getAdUnitId() {
        return this.zzpa;
    }
    
    public AppEventListener getAppEventListener() {
        return this.zztj;
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zztI;
    }
    
    public String getMediationAdapterClassName() {
        try {
            if (this.zztF != null) {
                return this.zztF.getMediationAdapterClassName();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get the mediation adapter class name.", (Throwable)ex);
        }
        return null;
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zztK;
    }
    
    public void setAdListener(final AdListener zzsz) {
        try {
            this.zzsz = zzsz;
            if (this.zztF != null) {
                final zzs zztF = this.zztF;
                zzc zzc;
                if (zzsz != null) {
                    zzc = new zzc(zzsz);
                }
                else {
                    zzc = null;
                }
                zztF.zza(zzc);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void setAdSizes(final AdSize... array) {
        if (this.zztk != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.zza(array);
    }
    
    public void setAdUnitId(final String zzpa) {
        if (this.zzpa != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzpa = zzpa;
    }
    
    public void setAppEventListener(final AppEventListener zztj) {
        try {
            this.zztj = zztj;
            if (this.zztF != null) {
                final zzs zztF = this.zztF;
                zzj zzj;
                if (zztj != null) {
                    zzj = new zzj(zztj);
                }
                else {
                    zzj = null;
                }
                zztF.zza(zzj);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener zztI) {
        if (this.zztJ != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.zztI = zztI;
            if (this.zztF != null) {
                final zzs zztF = this.zztF;
                zzfx zzfx;
                if (zztI != null) {
                    zzfx = new zzfx(zztI);
                }
                else {
                    zzfx = null;
                }
                zztF.zza(zzfx);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void setManualImpressionsEnabled(final boolean zzoN) {
        this.zzoN = zzoN;
        try {
            if (this.zztF != null) {
                this.zztF.setManualImpressionsEnabled(this.zzoN);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set manual impressions.", (Throwable)ex);
        }
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zztK) {
        this.zztK = zztK;
        try {
            if (this.zztF != null) {
                final zzs zztF = this.zztF;
                zzcl zzcl;
                if (zztK != null) {
                    zzcl = new zzcl(zztK);
                }
                else {
                    zzcl = null;
                }
                zztF.zza(zzcl);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", (Throwable)ex);
        }
    }
    
    public void zza(final zza zzsy) {
        try {
            this.zzsy = zzsy;
            if (this.zztF != null) {
                final zzs zztF = this.zztF;
                com.google.android.gms.ads.internal.client.zzb zzb;
                if (zzsy != null) {
                    zzb = new com.google.android.gms.ads.internal.client.zzb(zzsy);
                }
                else {
                    zzb = null;
                }
                zztF.zza(zzb);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AdClickListener.", (Throwable)ex);
        }
    }
    
    public void zza(final AdSize... zztk) {
        this.zztk = zztk;
        while (true) {
            try {
                if (this.zztF != null) {
                    this.zztF.zza(new AdSizeParcel(this.zztH.getContext(), this.zztk));
                }
                this.zztH.requestLayout();
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
