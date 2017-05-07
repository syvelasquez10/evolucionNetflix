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
    final int zzCY;
    private boolean zzWX;
    private ConnectionResult zzYg;
    IBinder zzZN;
    private boolean zzabc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzw();
    }
    
    public ResolveAccountResponse(final int n) {
        this(new ConnectionResult(n, null));
    }
    
    ResolveAccountResponse(final int zzCY, final IBinder zzZN, final ConnectionResult zzYg, final boolean zzWX, final boolean zzabc) {
        this.zzCY = zzCY;
        this.zzZN = zzZN;
        this.zzYg = zzYg;
        this.zzWX = zzWX;
        this.zzabc = zzabc;
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
            if (!this.zzYg.equals(resolveAccountResponse.zzYg) || !this.zznX().equals(resolveAccountResponse.zznX())) {
                return false;
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzw.zza(this, parcel, n);
    }
    
    public IAccountAccessor zznX() {
        return IAccountAccessor$zza.zzaD(this.zzZN);
    }
    
    public ConnectionResult zznY() {
        return this.zzYg;
    }
    
    public boolean zznZ() {
        return this.zzWX;
    }
    
    public boolean zzoa() {
        return this.zzabc;
    }
}
