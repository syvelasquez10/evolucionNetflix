// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.accounts.Account;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountRequest> CREATOR;
    final int mVersionCode;
    private final Account zzQd;
    private final int zzagp;
    
    static {
        CREATOR = (Parcelable$Creator)new zzy();
    }
    
    ResolveAccountRequest(final int mVersionCode, final Account zzQd, final int zzagp) {
        this.mVersionCode = mVersionCode;
        this.zzQd = zzQd;
        this.zzagp = zzagp;
    }
    
    public ResolveAccountRequest(final Account account, final int n) {
        this(1, account, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzQd;
    }
    
    public int getSessionId() {
        return this.zzagp;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzy.zza(this, parcel, n);
    }
}
