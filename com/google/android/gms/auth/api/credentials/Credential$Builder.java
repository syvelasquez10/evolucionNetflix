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
    private Uri zzSh;
    private List<IdToken> zzSi;
    private String zzSj;
    private String zzSk;
    private String zzSl;
    private String zzSm;
    private final String zzwN;
    
    public Credential$Builder(final String zzwN) {
        this.zzwN = zzwN;
    }
    
    public Credential build() {
        if (!TextUtils.isEmpty((CharSequence)this.zzSj) && !TextUtils.isEmpty((CharSequence)this.zzSk)) {
            throw new IllegalStateException("Only one of password or accountType may be set");
        }
        return new Credential(3, this.zzwN, this.mName, this.zzSh, this.zzSi, this.zzSj, this.zzSk, this.zzSl, this.zzSm);
    }
    
    public Credential$Builder setPassword(final String zzSj) {
        this.zzSj = zzSj;
        return this;
    }
}
