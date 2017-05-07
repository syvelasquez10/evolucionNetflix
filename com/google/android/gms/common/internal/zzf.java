// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.internal.zzqx;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;

public final class zzf
{
    private final Account zzQd;
    private final String zzRq;
    private final Set<Scope> zzaaF;
    private final int zzaaG;
    private final View zzaaH;
    private final String zzaaI;
    private final zzqx zzaaT;
    private final Set<Scope> zzafh;
    private final Map<Api<?>, zzf$zza> zzafi;
    private Integer zzafj;
    
    public zzf(final Account zzQd, final Set<Scope> set, final Map<Api<?>, zzf$zza> map, final int zzaaG, final View zzaaH, final String zzRq, final String zzaaI, final zzqx zzaaT) {
        this.zzQd = zzQd;
        Set<Scope> zzaaF;
        if (set == null) {
            zzaaF = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            zzaaF = Collections.unmodifiableSet((Set<? extends Scope>)set);
        }
        this.zzaaF = zzaaF;
        Map<Api<?>, zzf$zza> empty_MAP = map;
        if (map == null) {
            empty_MAP = (Map<Api<?>, zzf$zza>)Collections.EMPTY_MAP;
        }
        this.zzafi = empty_MAP;
        this.zzaaH = zzaaH;
        this.zzaaG = zzaaG;
        this.zzRq = zzRq;
        this.zzaaI = zzaaI;
        this.zzaaT = zzaaT;
        final HashSet<Scope> set2 = new HashSet<Scope>(this.zzaaF);
        final Iterator<zzf$zza> iterator = this.zzafi.values().iterator();
        while (iterator.hasNext()) {
            set2.addAll((Collection<?>)iterator.next().zzTm);
        }
        this.zzafh = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set2);
    }
    
    public Account getAccount() {
        return this.zzQd;
    }
    
    @Deprecated
    public String getAccountName() {
        if (this.zzQd != null) {
            return this.zzQd.name;
        }
        return null;
    }
    
    public void zza(final Integer zzafj) {
        this.zzafj = zzafj;
    }
    
    public Set<Scope> zzb(final Api<?> api) {
        final zzf$zza zzf$zza = this.zzafi.get(api);
        if (zzf$zza == null || zzf$zza.zzTm.isEmpty()) {
            return this.zzaaF;
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzaaF);
        set.addAll(zzf$zza.zzTm);
        return (Set<Scope>)set;
    }
    
    public Account zzoI() {
        if (this.zzQd != null) {
            return this.zzQd;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    public Set<Scope> zzoK() {
        return this.zzaaF;
    }
    
    public Set<Scope> zzoL() {
        return this.zzafh;
    }
    
    public Map<Api<?>, zzf$zza> zzoM() {
        return this.zzafi;
    }
    
    public String zzoN() {
        return this.zzRq;
    }
    
    public String zzoO() {
        return this.zzaaI;
    }
    
    public zzqx zzoQ() {
        return this.zzaaT;
    }
    
    public Integer zzoR() {
        return this.zzafj;
    }
}
