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
    private final Account zzOY;
    private final String zzRU;
    private final Scope[] zzaOm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    RecordConsentRequest(final int mVersionCode, final Account zzOY, final Scope[] zzaOm, final String zzRU) {
        this.mVersionCode = mVersionCode;
        this.zzOY = zzOY;
        this.zzaOm = zzaOm;
        this.zzRU = zzRU;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzOY;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public String zzlG() {
        return this.zzRU;
    }
    
    public Scope[] zzzs() {
        return this.zzaOm;
    }
}
