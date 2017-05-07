// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.app.PendingIntent;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountResponse implements SafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountResponse> CREATOR;
    final int mVersionCode;
    private boolean zzZF;
    private ConnectionResult zzaaV;
    IBinder zzacC;
    private boolean zzaen;
    
    static {
        CREATOR = (Parcelable$Creator)new zzz();
    }
    
    public ResolveAccountResponse(final int n) {
        this(new ConnectionResult(n, null));
    }
    
    ResolveAccountResponse(final int mVersionCode, final IBinder zzacC, final ConnectionResult zzaaV, final boolean zzZF, final boolean zzaen) {
        this.mVersionCode = mVersionCode;
        this.zzacC = zzacC;
        this.zzaaV = zzaaV;
        this.zzZF = zzZF;
        this.zzaen = zzaen;
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
            if (!this.zzaaV.equals(resolveAccountResponse.zzaaV) || !this.zzoO().equals(resolveAccountResponse.zzoO())) {
                return false;
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzz.zza(this, parcel, n);
    }
    
    public zzp zzoO() {
        return zzp$zza.zzaH(this.zzacC);
    }
    
    public ConnectionResult zzoP() {
        return this.zzaaV;
    }
    
    public boolean zzoQ() {
        return this.zzZF;
    }
    
    public boolean zzoR() {
        return this.zzaen;
    }
}
