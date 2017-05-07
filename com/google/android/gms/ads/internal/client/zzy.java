// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzci;
import com.google.android.gms.internal.zzfm;
import com.google.android.gms.internal.zzfr;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;

public class zzy
{
    private boolean zzoM;
    private String zzoZ;
    private AppEventListener zzsK;
    private AdSize[] zzsL;
    private zza zzsn;
    private AdListener zzso;
    private zzr zztm;
    private ViewGroup zzto;
    private InAppPurchaseListener zztp;
    private PlayStorePurchaseListener zztq;
    private OnCustomRenderedAdLoadedListener zztr;
    
    public AdListener getAdListener() {
        return this.zzso;
    }
    
    public AdSize getAdSize() {
        try {
            if (this.zztm != null) {
                final AdSizeParcel zzaN = this.zztm.zzaN();
                if (zzaN != null) {
                    return zzaN.zzcC();
                }
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get the current AdSize.", (Throwable)ex);
        }
        if (this.zzsL != null) {
            return this.zzsL[0];
        }
        return null;
    }
    
    public AdSize[] getAdSizes() {
        return this.zzsL;
    }
    
    public String getAdUnitId() {
        return this.zzoZ;
    }
    
    public AppEventListener getAppEventListener() {
        return this.zzsK;
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zztp;
    }
    
    public String getMediationAdapterClassName() {
        try {
            if (this.zztm != null) {
                return this.zztm.getMediationAdapterClassName();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get the mediation adapter class name.", (Throwable)ex);
        }
        return null;
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zztr;
    }
    
    public void setAdListener(final AdListener zzso) {
        try {
            this.zzso = zzso;
            if (this.zztm != null) {
                final zzr zztm = this.zztm;
                zzc zzc;
                if (zzso != null) {
                    zzc = new zzc(zzso);
                }
                else {
                    zzc = null;
                }
                zztm.zza(zzc);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void setAdSizes(final AdSize... array) {
        if (this.zzsL != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.zza(array);
    }
    
    public void setAdUnitId(final String zzoZ) {
        if (this.zzoZ != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.zzoZ = zzoZ;
    }
    
    public void setAppEventListener(final AppEventListener zzsK) {
        try {
            this.zzsK = zzsK;
            if (this.zztm != null) {
                final zzr zztm = this.zztm;
                zzi zzi;
                if (zzsK != null) {
                    zzi = new zzi(zzsK);
                }
                else {
                    zzi = null;
                }
                zztm.zza(zzi);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener zztp) {
        if (this.zztq != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.zztp = zztp;
            if (this.zztm != null) {
                final zzr zztm = this.zztm;
                zzfr zzfr;
                if (zztp != null) {
                    zzfr = new zzfr(zztp);
                }
                else {
                    zzfr = null;
                }
                zztm.zza(zzfr);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void setManualImpressionsEnabled(final boolean zzoM) {
        this.zzoM = zzoM;
        try {
            if (this.zztm != null) {
                this.zztm.setManualImpressionsEnabled(this.zzoM);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set manual impressions.", (Throwable)ex);
        }
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zztr) {
        this.zztr = zztr;
        try {
            if (this.zztm != null) {
                final zzr zztm = this.zztm;
                zzci zzci;
                if (zztr != null) {
                    zzci = new zzci(zztr);
                }
                else {
                    zzci = null;
                }
                zztm.zza(zzci);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", (Throwable)ex);
        }
    }
    
    public void zza(final zza zzsn) {
        try {
            this.zzsn = zzsn;
            if (this.zztm != null) {
                final zzr zztm = this.zztm;
                com.google.android.gms.ads.internal.client.zzb zzb;
                if (zzsn != null) {
                    zzb = new com.google.android.gms.ads.internal.client.zzb(zzsn);
                }
                else {
                    zzb = null;
                }
                zztm.zza(zzb);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AdClickListener.", (Throwable)ex);
        }
    }
    
    public void zza(final AdSize... zzsL) {
        this.zzsL = zzsL;
        while (true) {
            try {
                if (this.zztm != null) {
                    this.zztm.zza(new AdSizeParcel(this.zzto.getContext(), this.zzsL));
                }
                this.zzto.requestLayout();
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
}
