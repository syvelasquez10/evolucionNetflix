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
    private final Account zzOY;
    private final int zzaem;
    
    static {
        CREATOR = (Parcelable$Creator)new zzy();
    }
    
    ResolveAccountRequest(final int mVersionCode, final Account zzOY, final int zzaem) {
        this.mVersionCode = mVersionCode;
        this.zzOY = zzOY;
        this.zzaem = zzaem;
    }
    
    public ResolveAccountRequest(final Account account, final int n) {
        this(1, account, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzOY;
    }
    
    public int getSessionId() {
        return this.zzaem;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzy.zza(this, parcel, n);
    }
}
