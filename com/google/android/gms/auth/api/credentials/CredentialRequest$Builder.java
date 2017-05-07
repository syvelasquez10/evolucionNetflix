// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

public final class CredentialRequest$Builder
{
    boolean zzOX;
    String[] zzOY;
    
    public CredentialRequest build() {
        if (this.zzOY == null) {
            this.zzOY = new String[0];
        }
        if (!this.zzOX && this.zzOY.length == 0) {
            throw new IllegalStateException("At least one authentication method must be specified");
        }
        return new CredentialRequest(this, null);
    }
    
    public CredentialRequest$Builder setSupportsPasswordLogin(final boolean zzOX) {
        this.zzOX = zzOX;
        return this;
    }
}
