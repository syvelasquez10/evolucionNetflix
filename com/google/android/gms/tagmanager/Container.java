// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.HashMap;
import com.google.android.gms.internal.zzqp$zzc;
import java.util.Map;
import android.content.Context;

public class Container
{
    private final Context mContext;
    private final String zzaOS;
    private final DataLayer zzaOT;
    private zzcp zzaOU;
    private Map<String, Container$FunctionCallMacroCallback> zzaOV;
    private Map<String, Container$FunctionCallTagCallback> zzaOW;
    private volatile long zzaOX;
    private volatile String zzaOY;
    
    Container(final Context mContext, final DataLayer zzaOT, final String zzaOS, final long zzaOX, final zzqp$zzc zzqp$zzc) {
        this.zzaOV = new HashMap<String, Container$FunctionCallMacroCallback>();
        this.zzaOW = new HashMap<String, Container$FunctionCallTagCallback>();
        this.zzaOY = "";
        this.mContext = mContext;
        this.zzaOT = zzaOT;
        this.zzaOS = zzaOS;
        this.zzaOX = zzaOX;
        this.zza(zzqp$zzc);
    }
    
    private void zza(final zzqp$zzc zzqp$zzc) {
        this.zzaOY = zzqp$zzc.getVersion();
        this.zza(new zzcp(this.mContext, zzqp$zzc, this.zzaOT, new Container$zza(this, null), new Container$zzb(this, null), this.zzex(this.zzaOY)));
        if (this.getBoolean("_gtm.loadEventEnabled")) {
            this.zzaOT.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzaOS));
        }
    }
    
    private void zza(final zzcp zzaOU) {
        synchronized (this) {
            this.zzaOU = zzaOU;
        }
    }
    
    private zzcp zzzD() {
        synchronized (this) {
            return this.zzaOU;
        }
    }
    
    public boolean getBoolean(final String s) {
        final zzcp zzzD = this.zzzD();
        if (zzzD == null) {
            zzbg.e("getBoolean called for closed container.");
            return zzdf.zzBd();
        }
        try {
            return zzdf.zzk(zzzD.zzeS(s).getObject());
        }
        catch (Exception ex) {
            zzbg.e("Calling getBoolean() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzBd();
        }
    }
    
    public long getLastRefreshTime() {
        return this.zzaOX;
    }
    
    public String getString(String zzg) {
        final zzcp zzzD = this.zzzD();
        if (zzzD == null) {
            zzbg.e("getString called for closed container.");
            return zzdf.zzBf();
        }
        try {
            zzg = zzdf.zzg(zzzD.zzeS(zzg).getObject());
            return zzg;
        }
        catch (Exception ex) {
            zzbg.e("Calling getString() threw an exception: " + ex.getMessage() + " Returning default value.");
            return zzdf.zzBf();
        }
    }
    
    public boolean isDefault() {
        return this.getLastRefreshTime() == 0L;
    }
    
    void release() {
        this.zzaOU = null;
    }
    
    Container$FunctionCallMacroCallback zzeu(final String s) {
        synchronized (this.zzaOV) {
            return this.zzaOV.get(s);
        }
    }
    
    Container$FunctionCallTagCallback zzev(final String s) {
        synchronized (this.zzaOW) {
            return this.zzaOW.get(s);
        }
    }
    
    void zzew(final String s) {
        this.zzzD().zzew(s);
    }
    
    zzah zzex(final String s) {
        if (zzcb.zzAv().zzAw().equals(zzcb$zza.zzaRe)) {}
        return new zzbo();
    }
}
