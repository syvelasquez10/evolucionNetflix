// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import android.accounts.Account;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RecordConsentRequest implements SafeParcelable
{
    public static final Parcelable$Creator<RecordConsentRequest> CREATOR;
    final int mVersionCode;
    private final Account zzQd;
    private final String zzTl;
    private final Scope[] zzaVk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    RecordConsentRequest(final int mVersionCode, final Account zzQd, final Scope[] zzaVk, final String zzTl) {
        this.mVersionCode = mVersionCode;
        this.zzQd = zzQd;
        this.zzaVk = zzaVk;
        this.zzTl = zzTl;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzQd;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public Scope[] zzCj() {
        return this.zzaVk;
    }
    
    public String zzmb() {
        return this.zzTl;
    }
}
