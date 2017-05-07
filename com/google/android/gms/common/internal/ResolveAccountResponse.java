// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountResponse implements SafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountResponse> CREATOR;
    final int mVersionCode;
    private boolean zzabG;
    IBinder zzaeH;
    private ConnectionResult zzagq;
    private boolean zzagr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzz();
    }
    
    public ResolveAccountResponse(final int n) {
        this(new ConnectionResult(n, null));
    }
    
    ResolveAccountResponse(final int mVersionCode, final IBinder zzaeH, final ConnectionResult zzagq, final boolean zzabG, final boolean zzagr) {
        this.mVersionCode = mVersionCode;
        this.zzaeH = zzaeH;
        this.zzagq = zzagq;
        this.zzabG = zzabG;
        this.zzagr = zzagr;
    }
    
    public ResolveAccountResponse(final ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ResolveAccountResponse)) {
                return false;
            }
            final ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse)o;
            if (!this.zzagq.equals(resolveAccountResponse.zzagq) || !this.zzpq().equals(resolveAccountResponse.zzpq())) {
                return false;
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzz.zza(this, parcel, n);
    }
    
    public zzp zzpq() {
        return zzp$zza.zzaH(this.zzaeH);
    }
    
    public ConnectionResult zzpr() {
        return this.zzagq;
    }
    
    public boolean zzps() {
        return this.zzabG;
    }
    
    public boolean zzpt() {
        return this.zzagr;
    }
}
