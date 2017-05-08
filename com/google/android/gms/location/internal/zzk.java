// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import java.util.Iterator;
import com.google.android.gms.location.zzc;
import android.os.RemoteException;
import com.google.android.gms.location.zzd;
import java.util.HashMap;
import java.util.Map;
import android.content.ContentProviderClient;
import android.content.Context;

public class zzk
{
    private final Context mContext;
    private final zzp<zzi> zzaFb;
    private ContentProviderClient zzaFv;
    private boolean zzaFw;
    private Map<Object, zzk$zza> zzaFx;
    private Map<Object, zzk$zzc> zzaqR;
    
    public zzk(final Context mContext, final zzp<zzi> zzaFb) {
        this.zzaFv = null;
        this.zzaFw = false;
        this.zzaqR = new HashMap<Object, zzk$zzc>();
        this.zzaFx = new HashMap<Object, zzk$zza>();
        this.mContext = mContext;
        this.zzaFb = zzaFb;
    }
    
    public void removeAllListeners() {
        try {
            synchronized (this.zzaqR) {
                for (final zzk$zzc zzk$zzc : this.zzaqR.values()) {
                    if (zzk$zzc != null) {
                        this.zzaFb.zzpc().zza(LocationRequestUpdateData.zza(zzk$zzc, null));
                    }
                }
            }
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
        this.zzaqR.clear();
        for (final zzk$zza zzk$zza : this.zzaFx.values()) {
            if (zzk$zza != null) {
                this.zzaFb.zzpc().zza(LocationRequestUpdateData.zza(zzk$zza, null));
            }
        }
        this.zzaFx.clear();
    }
    // monitorexit(map)
    
    public void zzah(final boolean zzaFw) {
        this.zzaFb.zzpb();
        this.zzaFb.zzpc().zzah(zzaFw);
        this.zzaFw = zzaFw;
    }
    
    public void zzwE() {
        if (!this.zzaFw) {
            return;
        }
        try {
            this.zzah(false);
        }
        catch (RemoteException ex) {
            throw new IllegalStateException((Throwable)ex);
        }
    }
}
