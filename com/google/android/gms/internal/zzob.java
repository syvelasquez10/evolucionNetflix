// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzob
{
    private final zzoe zzaHQ;
    private boolean zzaHR;
    private long zzaHS;
    private long zzaHT;
    private long zzaHU;
    private long zzaHV;
    private long zzaHW;
    private boolean zzaHX;
    private final Map<Class<? extends zzod>, zzod> zzaHY;
    private final List<zzoh> zzaHZ;
    private final zzlm zzpO;
    
    zzob(final zzob zzob) {
        this.zzaHQ = zzob.zzaHQ;
        this.zzpO = zzob.zzpO;
        this.zzaHS = zzob.zzaHS;
        this.zzaHT = zzob.zzaHT;
        this.zzaHU = zzob.zzaHU;
        this.zzaHV = zzob.zzaHV;
        this.zzaHW = zzob.zzaHW;
        this.zzaHZ = new ArrayList<zzoh>(zzob.zzaHZ);
        this.zzaHY = new HashMap<Class<? extends zzod>, zzod>(zzob.zzaHY.size());
        for (final Map.Entry<Class<? extends zzod>, zzod> entry : zzob.zzaHY.entrySet()) {
            final zzod zzf = zzf((Class<zzod>)entry.getKey());
            entry.getValue().zza(zzf);
            this.zzaHY.put(entry.getKey(), zzf);
        }
    }
    
    zzob(final zzoe zzaHQ, final zzlm zzpO) {
        zzx.zzv(zzaHQ);
        zzx.zzv(zzpO);
        this.zzaHQ = zzaHQ;
        this.zzpO = zzpO;
        this.zzaHV = 1800000L;
        this.zzaHW = 3024000000L;
        this.zzaHY = new HashMap<Class<? extends zzod>, zzod>();
        this.zzaHZ = new ArrayList<zzoh>();
    }
    
    private static <T extends zzod> T zzf(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", ex2);
        }
    }
    
    public void zzL(final long zzaHT) {
        this.zzaHT = zzaHT;
    }
    
    public void zzb(final zzod zzod) {
        zzx.zzv(zzod);
        final Class<? extends zzod> class1 = zzod.getClass();
        if (class1.getSuperclass() != zzod.class) {
            throw new IllegalArgumentException();
        }
        zzod.zza(this.zze(class1));
    }
    
    public <T extends zzod> T zzd(final Class<T> clazz) {
        return (T)this.zzaHY.get(clazz);
    }
    
    public <T extends zzod> T zze(final Class<T> clazz) {
        zzod zzf;
        if ((zzf = this.zzaHY.get(clazz)) == null) {
            zzf = zzf((Class<zzod>)clazz);
            this.zzaHY.put(clazz, zzf);
        }
        return (T)zzf;
    }
    
    public zzob zzxh() {
        return new zzob(this);
    }
    
    public Collection<zzod> zzxi() {
        return (Collection<zzod>)this.zzaHY.values();
    }
    
    public List<zzoh> zzxj() {
        return this.zzaHZ;
    }
    
    public long zzxk() {
        return this.zzaHS;
    }
    
    public void zzxl() {
        this.zzxp().zze(this);
    }
    
    public boolean zzxm() {
        return this.zzaHR;
    }
    
    void zzxn() {
        this.zzaHU = this.zzpO.elapsedRealtime();
        if (this.zzaHT != 0L) {
            this.zzaHS = this.zzaHT;
        }
        else {
            this.zzaHS = this.zzpO.currentTimeMillis();
        }
        this.zzaHR = true;
    }
    
    zzoe zzxo() {
        return this.zzaHQ;
    }
    
    zzof zzxp() {
        return this.zzaHQ.zzxp();
    }
    
    boolean zzxq() {
        return this.zzaHX;
    }
    
    void zzxr() {
        this.zzaHX = true;
    }
}
