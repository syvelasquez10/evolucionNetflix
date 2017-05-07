// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.text.TextUtils;
import java.util.List;
import android.net.Uri;

public class Credential$Builder
{
    private String mName;
    private String zzRa;
    private String zzRb;
    private Uri zzRc;
    private List<IdToken> zzRd;
    private String zzRe;
    private String zzRf;
    private String zzRg;
    private String zzRh;
    private final String zzwj;
    
    public Credential$Builder(final String zzwj) {
        this.zzwj = zzwj;
    }
    
    public Credential build() {
        if (!TextUtils.isEmpty((CharSequence)this.zzRe) && !TextUtils.isEmpty((CharSequence)this.zzRf)) {
            throw new IllegalStateException("Only one of password or accountType may be set");
        }
        return new Credential(2, this.zzRa, this.zzRb, this.zzwj, this.mName, this.zzRc, this.zzRd, this.zzRe, this.zzRf, this.zzRg, this.zzRh);
    }
    
    public Credential$Builder setPassword(final String zzRe) {
        this.zzRe = zzRe;
        return this;
    }
}
