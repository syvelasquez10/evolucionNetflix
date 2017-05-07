// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

public class CredentialPickerConfig$Builder
{
    private boolean mShowCancelButton;
    private boolean zzSn;
    
    public CredentialPickerConfig$Builder() {
        this.zzSn = false;
        this.mShowCancelButton = true;
    }
    
    public CredentialPickerConfig build() {
        return new CredentialPickerConfig(this, null);
    }
}
