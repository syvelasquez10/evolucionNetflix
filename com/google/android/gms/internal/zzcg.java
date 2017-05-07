// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class zzcg
{
    private final Object zzpd;
    boolean zzvA;
    private final List<zzce> zzvR;
    private final Map<String, String> zzvS;
    private zzcg zzvV;
    
    public zzcg(final boolean zzvA, final String s, final String s2) {
        this.zzvR = new LinkedList<zzce>();
        this.zzvS = new LinkedHashMap<String, String>();
        this.zzpd = new Object();
        this.zzvA = zzvA;
        this.zzvS.put("action", s);
        this.zzvS.put("ad_format", s2);
    }
    
    public boolean zza(final zzce zzce, final long n, final String... array) {
        synchronized (this.zzpd) {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zzvR.add(new zzce(n, array[i], zzce));
            }
            return true;
        }
    }
    
    public boolean zza(final zzce zzce, final String... array) {
        return this.zzvA && zzce != null && this.zza(zzce, zzp.zzbz().elapsedRealtime(), array);
    }
    
    public zzce zzb(final long n) {
        if (!this.zzvA) {
            return null;
        }
        return new zzce(n, null, null);
    }
    
    public void zzc(final zzcg zzvV) {
        synchronized (this.zzpd) {
            this.zzvV = zzvV;
        }
    }
    
    public zzce zzdn() {
        return this.zzb(zzp.zzbz().elapsedRealtime());
    }
    
    public void zze(final String s, final String s2) {
        if (this.zzvA && !TextUtils.isEmpty((CharSequence)s2)) {
            final zzca zzgo = zzp.zzby().zzgo();
            if (zzgo != null) {
                synchronized (this.zzpd) {
                    zzgo.zzR(s).zza(this.zzvS, s, s2);
                }
            }
        }
    }
}
