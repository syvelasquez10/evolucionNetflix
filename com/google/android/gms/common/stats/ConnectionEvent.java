// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConnectionEvent extends zzf implements SafeParcelable
{
    public static final Parcelable$Creator<ConnectionEvent> CREATOR;
    final int mVersionCode;
    private final long zzahn;
    private int zzaho;
    private final String zzahp;
    private final String zzahq;
    private final String zzahr;
    private final String zzahs;
    private final String zzaht;
    private final String zzahu;
    private final long zzahv;
    private final long zzahw;
    private long zzahx;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ConnectionEvent(final int mVersionCode, final long zzahn, final int zzaho, final String zzahp, final String zzahq, final String zzahr, final String zzahs, final String zzaht, final String zzahu, final long zzahv, final long zzahw) {
        this.mVersionCode = mVersionCode;
        this.zzahn = zzahn;
        this.zzaho = zzaho;
        this.zzahp = zzahp;
        this.zzahq = zzahq;
        this.zzahr = zzahr;
        this.zzahs = zzahs;
        this.zzahx = -1L;
        this.zzaht = zzaht;
        this.zzahu = zzahu;
        this.zzahv = zzahv;
        this.zzahw = zzahw;
    }
    
    public ConnectionEvent(final long n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final long n3, final long n4) {
        this(1, n, n2, s, s2, s3, s4, s5, s6, n3, n4);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public int getEventType() {
        return this.zzaho;
    }
    
    @Override
    public long getTimeMillis() {
        return this.zzahn;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzpX() {
        return this.zzahp;
    }
    
    public String zzpY() {
        return this.zzahq;
    }
    
    public String zzpZ() {
        return this.zzahr;
    }
    
    public String zzqa() {
        return this.zzahs;
    }
    
    public String zzqb() {
        return this.zzaht;
    }
    
    public String zzqc() {
        return this.zzahu;
    }
    
    @Override
    public long zzqd() {
        return this.zzahx;
    }
    
    public long zzqe() {
        return this.zzahw;
    }
    
    public long zzqf() {
        return this.zzahv;
    }
    
    @Override
    public String zzqg() {
        final StringBuilder append = new StringBuilder().append("\t").append(this.zzpX()).append("/").append(this.zzpY()).append("\t").append(this.zzpZ()).append("/").append(this.zzqa()).append("\t");
        String zzaht;
        if (this.zzaht == null) {
            zzaht = "";
        }
        else {
            zzaht = this.zzaht;
        }
        return append.append(zzaht).append("\t").append(this.zzqe()).toString();
    }
}
