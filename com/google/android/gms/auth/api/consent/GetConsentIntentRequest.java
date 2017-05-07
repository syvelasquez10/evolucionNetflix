// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.consent;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import android.accounts.Account;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetConsentIntentRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GetConsentIntentRequest> CREATOR;
    private final int mVersionCode;
    private final Account zzQd;
    private final String zzSb;
    private final int zzSc;
    private final String zzSd;
    final ScopeDetail[] zzSe;
    private final boolean zzSf;
    private final int zzSg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    GetConsentIntentRequest(final int mVersionCode, final String zzSb, final int zzSc, final String zzSd, final Account account, final ScopeDetail[] zzSe, final boolean zzSf, final int zzSg) {
        this.mVersionCode = mVersionCode;
        this.zzSb = zzSb;
        this.zzSc = zzSc;
        this.zzSd = zzSd;
        this.zzQd = zzx.zzw(account);
        this.zzSe = zzSe;
        this.zzSf = zzSf;
        this.zzSg = zzSg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzQd;
    }
    
    public String getCallingPackage() {
        return this.zzSb;
    }
    
    public int getCallingUid() {
        return this.zzSc;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public String zzlF() {
        return this.zzSd;
    }
    
    public boolean zzlG() {
        return this.zzSf;
    }
    
    public int zzlH() {
        return this.zzSg;
    }
}
