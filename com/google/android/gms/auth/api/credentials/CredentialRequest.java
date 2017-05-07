// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CredentialRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CredentialRequest> CREATOR;
    final int zzCY;
    private final boolean zzOX;
    private final String[] zzOY;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    CredentialRequest(final int zzCY, final boolean zzOX, final String[] zzOY) {
        this.zzCY = zzCY;
        this.zzOX = zzOX;
        this.zzOY = zzOY;
    }
    
    private CredentialRequest(final CredentialRequest$Builder credentialRequest$Builder) {
        this.zzCY = 1;
        this.zzOX = credentialRequest$Builder.zzOX;
        this.zzOY = credentialRequest$Builder.zzOY;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String[] getAccountTypes() {
        return this.zzOY;
    }
    
    public boolean getSupportsPasswordLogin() {
        return this.zzOX;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
