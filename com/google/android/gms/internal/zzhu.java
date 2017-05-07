// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.Resources;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;
import android.content.Context;

@zzgr
public class zzhu
{
    private Context mContext;
    private boolean zzGg;
    private boolean zzGh;
    private final String zzHP;
    private final zzhv zzHQ;
    private BigInteger zzHR;
    private final HashSet<Object> zzHS;
    private final HashMap<String, Object> zzHT;
    private boolean zzHU;
    private int zzHV;
    private zzca zzHW;
    private zzbk zzHX;
    private final LinkedList<Thread> zzHY;
    private Boolean zzHZ;
    private String zzIa;
    private boolean zzIb;
    private boolean zzIc;
    private boolean zzpA;
    private VersionInfoParcel zzpb;
    private final Object zzpd;
    private zzbj zzsa;
    private zzbi zzsb;
    private final zzgq zzsc;
    
    public zzhu(final zzid zzid) {
        this.zzpd = new Object();
        this.zzHR = BigInteger.ONE;
        this.zzHS = new HashSet<Object>();
        this.zzHT = new HashMap<String, Object>();
        this.zzHU = false;
        this.zzGg = true;
        this.zzHV = 0;
        this.zzpA = false;
        this.zzHW = null;
        this.zzGh = true;
        this.zzsa = null;
        this.zzHX = null;
        this.zzsb = null;
        this.zzHY = new LinkedList<Thread>();
        this.zzsc = null;
        this.zzHZ = null;
        this.zzIb = false;
        this.zzIc = false;
        this.zzHP = zzid.zzgD();
        this.zzHQ = new zzhv(this.zzHP);
    }
    
    public void zzb(final Boolean zzHZ) {
        synchronized (this.zzpd) {
            this.zzHZ = zzHZ;
        }
    }
    
    public void zzc(final Throwable t, final boolean b) {
        new zzgq(this.mContext, this.zzpb, null, null).zza(t, b);
    }
    
    public String zzd(final int n, final String s) {
        Resources resources;
        if (this.zzpb.zzJx) {
            resources = this.mContext.getResources();
        }
        else {
            resources = GooglePlayServicesUtil.getRemoteResource(this.mContext);
        }
        if (resources == null) {
            return s;
        }
        return resources.getString(n);
    }
    
    public zzca zzgo() {
        synchronized (this.zzpd) {
            return this.zzHW;
        }
    }
    
    public String zzgr() {
        synchronized (this.zzpd) {
            return this.zzIa;
        }
    }
    
    public Boolean zzgs() {
        synchronized (this.zzpd) {
            return this.zzHZ;
        }
    }
}
