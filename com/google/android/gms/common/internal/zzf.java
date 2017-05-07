// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;

public final class zzf
{
    private final Account zzOY;
    private final String zzQl;
    private final Set<Scope> zzYY;
    private final int zzYZ;
    private final View zzZa;
    private final String zzZb;
    private final Set<Scope> zzadc;
    private final Map<Api<?>, zzf$zza> zzadd;
    private final zze zzade;
    private Integer zzadf;
    
    public zzf(final Account zzOY, final Set<Scope> set, final Map<Api<?>, zzf$zza> map, final int zzYZ, final View zzZa, final String zzQl, final String zzZb, final zze zzade) {
        this.zzOY = zzOY;
        Set<Scope> zzYY;
        if (set == null) {
            zzYY = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            zzYY = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
        this.zzYY = zzYY;
        Map<Api<?>, zzf$zza> empty_MAP = map;
        if (map == null) {
            empty_MAP = (Map<Api<?>, zzf$zza>)Collections.EMPTY_MAP;
        }
        this.zzadd = empty_MAP;
        this.zzZa = zzZa;
        this.zzYZ = zzYZ;
        this.zzQl = zzQl;
        this.zzZb = zzZb;
        this.zzade = zzade;
        final HashSet<Scope> set2 = new HashSet<Scope>(this.zzYY);
        final Iterator<zzf$zza> iterator = this.zzadd.values().iterator();
        while (iterator.hasNext()) {
            set2.addAll((Collection<?>)iterator.next().zzZp);
        }
        this.zzadc = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set2);
    }
    
    public Account getAccount() {
        return this.zzOY;
    }
    
    @Deprecated
    public String getAccountName() {
        if (this.zzOY != null) {
            return this.zzOY.name;
        }
        return null;
    }
    
    public void zza(final Integer zzadf) {
        this.zzadf = zzadf;
    }
    
    public Set<Scope> zzb(final Api<?> api) {
        final zzf$zza zzf$zza = this.zzadd.get(api);
        if (zzf$zza == null || zzf$zza.zzZp.isEmpty()) {
            return this.zzYY;
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzYY);
        set.addAll(zzf$zza.zzZp);
        return (Set<Scope>)set;
    }
    
    public Account zzog() {
        if (this.zzOY != null) {
            return this.zzOY;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    public Set<Scope> zzoi() {
        return this.zzYY;
    }
    
    public Set<Scope> zzoj() {
        return this.zzadc;
    }
    
    public Map<Api<?>, zzf$zza> zzok() {
        return this.zzadd;
    }
    
    public String zzol() {
        return this.zzQl;
    }
    
    public String zzom() {
        return this.zzZb;
    }
    
    public zze zzoo() {
        return this.zzade;
    }
    
    public Integer zzop() {
        return this.zzadf;
    }
}
