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
    final int zzCY;
    private final Account zzMY;
    private final int zzabb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzv();
    }
    
    ResolveAccountRequest(final int zzCY, final Account zzMY, final int zzabb) {
        this.zzCY = zzCY;
        this.zzMY = zzMY;
        this.zzabb = zzabb;
    }
    
    public ResolveAccountRequest(final Account account, final int n) {
        this(1, account, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzMY;
    }
    
    public int getSessionId() {
        return this.zzabb;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzv.zza(this, parcel, n);
    }
}
