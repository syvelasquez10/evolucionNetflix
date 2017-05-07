// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

public class CredentialPickerConfig$Builder
{
    private boolean mShowCancelButton;
    private boolean zzRi;
    
    public CredentialPickerConfig$Builder() {
        this.zzRi = false;
        this.mShowCancelButton = true;
    }
    
    public CredentialPickerConfig build() {
        return new CredentialPickerConfig(this, null);
    }
}
