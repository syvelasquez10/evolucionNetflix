// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzx;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CredentialRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CredentialRequest> CREATOR;
    final int mVersionCode;
    private final boolean zzRj;
    private final String[] zzRk;
    private final CredentialPickerConfig zzRl;
    private final CredentialPickerConfig zzRm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    CredentialRequest(final int mVersionCode, final boolean zzRj, final String[] array, final CredentialPickerConfig credentialPickerConfig, final CredentialPickerConfig credentialPickerConfig2) {
        this.mVersionCode = mVersionCode;
        this.zzRj = zzRj;
        this.zzRk = zzx.zzv(array);
        CredentialPickerConfig build = credentialPickerConfig;
        if (credentialPickerConfig == null) {
            build = new CredentialPickerConfig$Builder().build();
        }
        this.zzRl = build;
        CredentialPickerConfig build2;
        if ((build2 = credentialPickerConfig2) == null) {
            build2 = new CredentialPickerConfig$Builder().build();
        }
        this.zzRm = build2;
    }
    
    private CredentialRequest(final CredentialRequest$Builder credentialRequest$Builder) {
        this(2, credentialRequest$Builder.zzRj, credentialRequest$Builder.zzRk, credentialRequest$Builder.zzRl, credentialRequest$Builder.zzRm);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String[] getAccountTypes() {
        return this.zzRk;
    }
    
    public CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzRm;
    }
    
    public CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzRl;
    }
    
    public boolean getSupportsPasswordLogin() {
        return this.zzRj;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
