// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class WakeLockEvent implements SafeParcelable
{
    public static final Parcelable$Creator<WakeLockEvent> CREATOR;
    final int mVersionCode;
    private final String zzafS;
    private final int zzafT;
    private final List<String> zzafU;
    private final String zzafV;
    private int zzafW;
    private final String zzafX;
    private final String zzafY;
    private final float zzafZ;
    private final long zzafj;
    private int zzafk;
    private final long zzafr;
    private long zzaft;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    WakeLockEvent(final int mVersionCode, final long zzafj, final int zzafk, final String zzafS, final int zzafT, final List<String> zzafU, final String zzafV, final long zzafr, final int zzafW, final String zzafX, final String zzafY, final float zzafZ) {
        this.mVersionCode = mVersionCode;
        this.zzafj = zzafj;
        this.zzafk = zzafk;
        this.zzafS = zzafS;
        this.zzafX = zzafX;
        this.zzafT = zzafT;
        this.zzaft = -1L;
        this.zzafU = zzafU;
        this.zzafV = zzafV;
        this.zzafr = zzafr;
        this.zzafW = zzafW;
        this.zzafY = zzafY;
        this.zzafZ = zzafZ;
    }
    
    public WakeLockEvent(final long n, final int n2, final String s, final int n3, final List<String> list, final String s2, final long n4, final int n5, final String s3, final String s4, final float n6) {
        this(1, n, n2, s, n3, list, s2, n4, n5, s3, s4, n6);
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
        zzg.zza(this, parcel, n);
    }
    
    public String zzpA() {
        return this.zzafV;
    }
    
    public long zzpC() {
        return this.zzafr;
    }
    
    public String zzpE() {
        return this.zzafS;
    }
    
    public String zzpF() {
        return this.zzafX;
    }
    
    public int zzpG() {
        return this.zzafT;
    }
    
    public List<String> zzpH() {
        return this.zzafU;
    }
    
    public int zzpI() {
        return this.zzafW;
    }
    
    public String zzpJ() {
        return this.zzafY;
    }
    
    public float zzpK() {
        return this.zzafZ;
    }
}
