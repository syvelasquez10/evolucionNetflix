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
    private final boolean zzSo;
    private final String[] zzSp;
    private final CredentialPickerConfig zzSq;
    private final CredentialPickerConfig zzSr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    CredentialRequest(final int mVersionCode, final boolean zzSo, final String[] array, final CredentialPickerConfig credentialPickerConfig, final CredentialPickerConfig credentialPickerConfig2) {
        this.mVersionCode = mVersionCode;
        this.zzSo = zzSo;
        this.zzSp = zzx.zzw(array);
        CredentialPickerConfig build = credentialPickerConfig;
        if (credentialPickerConfig == null) {
            build = new CredentialPickerConfig$Builder().build();
        }
        this.zzSq = build;
        CredentialPickerConfig build2;
        if ((build2 = credentialPickerConfig2) == null) {
            build2 = new CredentialPickerConfig$Builder().build();
        }
        this.zzSr = build2;
    }
    
    private CredentialRequest(final CredentialRequest$Builder credentialRequest$Builder) {
        this(2, credentialRequest$Builder.zzSo, credentialRequest$Builder.zzSp, credentialRequest$Builder.zzSq, credentialRequest$Builder.zzSr);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String[] getAccountTypes() {
        return this.zzSp;
    }
    
    public CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzSr;
    }
    
    public CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzSq;
    }
    
    public boolean getSupportsPasswordLogin() {
        return this.zzSo;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
