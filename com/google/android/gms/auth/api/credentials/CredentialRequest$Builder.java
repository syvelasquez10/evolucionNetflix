// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

public final class CredentialRequest$Builder
{
    private boolean zzRj;
    private String[] zzRk;
    private CredentialPickerConfig zzRl;
    private CredentialPickerConfig zzRm;
    
    public CredentialRequest build() {
        if (this.zzRk == null) {
            this.zzRk = new String[0];
        }
        if (!this.zzRj && this.zzRk.length == 0) {
            throw new IllegalStateException("At least one authentication method must be specified");
        }
        return new CredentialRequest(this, null);
    }
    
    public CredentialRequest$Builder setSupportsPasswordLogin(final boolean zzRj) {
        this.zzRj = zzRj;
        return this;
    }
}
