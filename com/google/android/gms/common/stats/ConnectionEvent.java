// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConnectionEvent implements SafeParcelable
{
    public static final Parcelable$Creator<ConnectionEvent> CREATOR;
    final int mVersionCode;
    private final long zzafj;
    private int zzafk;
    private final String zzafl;
    private final String zzafm;
    private final String zzafn;
    private final String zzafo;
    private final String zzafp;
    private final String zzafq;
    private final long zzafr;
    private final long zzafs;
    private long zzaft;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ConnectionEvent(final int mVersionCode, final long zzafj, final int zzafk, final String zzafl, final String zzafm, final String zzafn, final String zzafo, final String zzafp, final String zzafq, final long zzafr, final long zzafs) {
        this.mVersionCode = mVersionCode;
        this.zzafj = zzafj;
        this.zzafk = zzafk;
        this.zzafl = zzafl;
        this.zzafm = zzafm;
        this.zzafn = zzafn;
        this.zzafo = zzafo;
        this.zzaft = -1L;
        this.zzafp = zzafp;
        this.zzafq = zzafq;
        this.zzafr = zzafr;
        this.zzafs = zzafs;
    }
    
    public ConnectionEvent(final long n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final long n3, final long n4) {
        this(1, n, n2, s, s2, s3, s4, s5, s6, n3, n4);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getEventType() {
        return this.zzafk;
    }
    
    public long getTimeMillis() {
        return this.zzafj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzpA() {
        return this.zzafq;
    }
    
    public long zzpB() {
        return this.zzafs;
    }
    
    public long zzpC() {
        return this.zzafr;
    }
    
    public String zzpv() {
        return this.zzafl;
    }
    
    public String zzpw() {
        return this.zzafm;
    }
    
    public String zzpx() {
        return this.zzafn;
    }
    
    public String zzpy() {
        return this.zzafo;
    }
    
    public String zzpz() {
        return this.zzafp;
    }
}
