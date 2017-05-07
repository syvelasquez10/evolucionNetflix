// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Collections;
import java.util.Collection;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;

public final class zze
{
    private final Account zzMY;
    private final String zzOd;
    private final String zzOe;
    private final Set<Scope> zzWu;
    private final int zzWv;
    private final View zzWw;
    private final Set<Scope> zzZR;
    private final Map<Api<?>, zze$zza> zzZS;
    private final zzpt zzZT;
    private Integer zzZU;
    
    public zze(final Account zzMY, final Collection<Scope> collection, final Map<Api<?>, zze$zza> map, final int zzWv, final View zzWw, final String zzOe, final String zzOd, final zzpt zzZT) {
        this.zzMY = zzMY;
        Set<Scope> zzWu;
        if (collection == null) {
            zzWu = (Set<Scope>)Collections.EMPTY_SET;
        }
        else {
            zzWu = Collections.unmodifiableSet((Set<? extends Scope>)new HashSet<Scope>(collection));
        }
        this.zzWu = zzWu;
        Map<Api<?>, zze$zza> empty_MAP = map;
        if (map == null) {
            empty_MAP = (Map<Api<?>, zze$zza>)Collections.EMPTY_MAP;
        }
        this.zzZS = empty_MAP;
        this.zzWw = zzWw;
        this.zzWv = zzWv;
        this.zzOe = zzOe;
        this.zzOd = zzOd;
        this.zzZT = zzZT;
        final HashSet<Scope> set = new HashSet<Scope>(this.zzWu);
        final Iterator<zze$zza> iterator = this.zzZS.values().iterator();
        while (iterator.hasNext()) {
            set.addAll((Collection<?>)iterator.next().zzWI);
        }
        this.zzZR = (Set<Scope>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public Account getAccount() {
        return this.zzMY;
    }
    
    @Deprecated
    public String getAccountName() {
        if (this.zzMY != null) {
            return this.zzMY.name;
        }
        return null;
    }
    
    public void zza(final Integer zzZU) {
        this.zzZU = zzZU;
    }
    
    public Set<Scope> zzb(final Api<?> api) {
        final zze$zza zze$zza = this.zzZS.get(api);
        if (zze$zza == null || zze$zza.zzWI.isEmpty()) {
            return this.zzWu;
        }
        final HashSet<Object> set = (HashSet<Object>)new HashSet<Scope>(this.zzWu);
        set.addAll(zze$zza.zzWI);
        return (Set<Scope>)set;
    }
    
    public Integer zznA() {
        return this.zzZU;
    }
    
    public Account zznr() {
        if (this.zzMY != null) {
            return this.zzMY;
        }
        return new Account("<<default account>>", "com.google");
    }
    
    public Set<Scope> zznt() {
        return this.zzWu;
    }
    
    public Set<Scope> zznu() {
        return this.zzZR;
    }
    
    public Map<Api<?>, zze$zza> zznv() {
        return this.zzZS;
    }
    
    public String zznw() {
        return this.zzOe;
    }
    
    public String zznx() {
        return this.zzOd;
    }
    
    public zzpt zznz() {
        return this.zzZT;
    }
}
