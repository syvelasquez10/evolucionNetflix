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

@zzgk
public class zzhl
{
    private Context mContext;
    private boolean zzFr;
    private boolean zzFs;
    private final String zzGY;
    private final zzhm zzGZ;
    private BigInteger zzHa;
    private final HashSet<Object> zzHb;
    private final HashMap<String, Object> zzHc;
    private boolean zzHd;
    private int zzHe;
    private zzca zzHf;
    private zzbk zzHg;
    private final LinkedList<Thread> zzHh;
    private Boolean zzHi;
    private String zzHj;
    private boolean zzHk;
    private VersionInfoParcel zzpa;
    private final Object zzpc;
    private boolean zzpr;
    private zzbj zzrP;
    private zzbi zzrQ;
    private final zzgj zzrR;
    
    public zzhl(final zzhu zzhu) {
        this.zzpc = new Object();
        this.zzHa = BigInteger.ONE;
        this.zzHb = new HashSet<Object>();
        this.zzHc = new HashMap<String, Object>();
        this.zzHd = false;
        this.zzFr = true;
        this.zzHe = 0;
        this.zzpr = false;
        this.zzHf = null;
        this.zzFs = true;
        this.zzrP = null;
        this.zzHg = null;
        this.zzrQ = null;
        this.zzHh = new LinkedList<Thread>();
        this.zzrR = null;
        this.zzHi = null;
        this.zzHk = false;
        this.zzGY = zzhu.zzgs();
        this.zzGZ = new zzhm(this.zzGY);
    }
    
    public void zzb(final Boolean zzHi) {
        synchronized (this.zzpc) {
            this.zzHi = zzHi;
        }
    }
    
    public String zzc(final int n, final String s) {
        Resources resources;
        if (this.zzpa.zzIC) {
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
    
    public void zzc(final Throwable t, final boolean b) {
        new zzgj(this.mContext, this.zzpa, null, null).zza(t, b);
    }
    
    public zzca zzgc() {
        synchronized (this.zzpc) {
            return this.zzHf;
        }
    }
    
    public String zzgf() {
        synchronized (this.zzpc) {
            return this.zzHj;
        }
    }
    
    public Boolean zzgg() {
        synchronized (this.zzpc) {
            return this.zzHi;
        }
    }
}
