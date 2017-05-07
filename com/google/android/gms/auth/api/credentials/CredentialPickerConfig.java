// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CredentialPickerConfig implements SafeParcelable
{
    public static final Parcelable$Creator<CredentialPickerConfig> CREATOR;
    private final boolean mShowCancelButton;
    final int mVersionCode;
    private final boolean zzRi;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    CredentialPickerConfig(final int mVersionCode, final boolean zzRi, final boolean mShowCancelButton) {
        this.mVersionCode = mVersionCode;
        this.zzRi = zzRi;
        this.mShowCancelButton = mShowCancelButton;
    }
    
    private CredentialPickerConfig(final CredentialPickerConfig$Builder credentialPickerConfig$Builder) {
        this(1, credentialPickerConfig$Builder.zzRi, credentialPickerConfig$Builder.mShowCancelButton);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public boolean shouldShowAddAccountButton() {
        return this.zzRi;
    }
    
    public boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
