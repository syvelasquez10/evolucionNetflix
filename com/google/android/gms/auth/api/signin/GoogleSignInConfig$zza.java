// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;

public final class GoogleSignInConfig$zza
{
    private Account zzQd;
    private boolean zzTi;
    private boolean zzTj;
    private boolean zzTk;
    private String zzTl;
    private Set<Scope> zzTm;
    
    public GoogleSignInConfig$zza() {
        this.zzTm = new HashSet<Scope>(Arrays.asList(GoogleSignInConfig.zzTg));
    }
    
    public GoogleSignInConfig zzmc() {
        return new GoogleSignInConfig(this.zzTm, this.zzQd, this.zzTi, this.zzTj, this.zzTk, this.zzTl, null);
    }
}
