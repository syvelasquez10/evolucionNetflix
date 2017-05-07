// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.text.TextUtils;
import android.net.Uri;
import java.util.List;

public class Credential$Builder
{
    private List<IdToken> mIdTokens;
    private String mName;
    private final String zzKI;
    private String zzOS;
    private String zzOT;
    private Uri zzOU;
    private String zzOV;
    private String zzOW;
    
    public Credential$Builder(final String zzKI) {
        this.zzKI = zzKI;
    }
    
    public Credential build() {
        if (!TextUtils.isEmpty((CharSequence)this.zzOV) && !TextUtils.isEmpty((CharSequence)this.zzOW)) {
            throw new IllegalStateException("Only one of password or accountType may be set");
        }
        return new Credential(1, this.zzOS, this.zzOT, this.zzKI, this.mName, this.zzOU, this.mIdTokens, this.zzOV, this.zzOW);
    }
    
    public Credential$Builder setPassword(final String zzOV) {
        this.zzOV = zzOV;
        return this;
    }
}
