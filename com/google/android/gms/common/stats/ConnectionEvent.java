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
    final int zzCY;
    private final long zzabY;
    private int zzabZ;
    private final String zzaca;
    private final String zzacb;
    private final String zzacc;
    private final String zzacd;
    private final String zzace;
    private final String zzacf;
    private final long zzacg;
    private final long zzach;
    private long zzaci;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ConnectionEvent(final int zzCY, final long zzabY, final int zzabZ, final String zzaca, final String zzacb, final String zzacc, final String zzacd, final String zzace, final String zzacf, final long zzacg, final long zzach) {
        this.zzCY = zzCY;
        this.zzabY = zzabY;
        this.zzabZ = zzabZ;
        this.zzaca = zzaca;
        this.zzacb = zzacb;
        this.zzacc = zzacc;
        this.zzacd = zzacd;
        this.zzaci = -1L;
        this.zzace = zzace;
        this.zzacf = zzacf;
        this.zzacg = zzacg;
        this.zzach = zzach;
    }
    
    public ConnectionEvent(final long n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final long n3, final long n4) {
        this(1, n, n2, s, s2, s3, s4, s5, s6, n3, n4);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getEventType() {
        return this.zzabZ;
    }
    
    public long getTimeMillis() {
        return this.zzabY;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzoE() {
        return this.zzaca;
    }
    
    public String zzoF() {
        return this.zzacb;
    }
    
    public String zzoG() {
        return this.zzacc;
    }
    
    public String zzoH() {
        return this.zzacd;
    }
    
    public String zzoI() {
        return this.zzace;
    }
    
    public String zzoJ() {
        return this.zzacf;
    }
    
    public long zzoK() {
        return this.zzach;
    }
    
    public long zzoL() {
        return this.zzacg;
    }
}
