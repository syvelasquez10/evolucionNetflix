// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

public final class CredentialRequest$Builder
{
    private boolean zzSo;
    private String[] zzSp;
    private CredentialPickerConfig zzSq;
    private CredentialPickerConfig zzSr;
    
    public CredentialRequest build() {
        if (this.zzSp == null) {
            this.zzSp = new String[0];
        }
        if (!this.zzSo && this.zzSp.length == 0) {
            throw new IllegalStateException("At least one authentication method must be specified");
        }
        return new CredentialRequest(this, null);
    }
    
    public CredentialRequest$Builder setSupportsPasswordLogin(final boolean zzSo) {
        this.zzSo = zzSo;
        return this;
    }
}
