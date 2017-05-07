// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public class zzqp$zze
{
    private final List<zzqp$zza> zzaTW;
    private final List<zzqp$zza> zzaTX;
    private final List<zzqp$zza> zzaTY;
    private final List<zzqp$zza> zzaTZ;
    private final List<zzqp$zza> zzaUa;
    private final List<zzqp$zza> zzaUb;
    private final List<String> zzaUc;
    private final List<String> zzaUd;
    private final List<String> zzaUe;
    private final List<String> zzaUf;
    
    private zzqp$zze(final List<zzqp$zza> list, final List<zzqp$zza> list2, final List<zzqp$zza> list3, final List<zzqp$zza> list4, final List<zzqp$zza> list5, final List<zzqp$zza> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
        this.zzaTW = Collections.unmodifiableList((List<? extends zzqp$zza>)list);
        this.zzaTX = Collections.unmodifiableList((List<? extends zzqp$zza>)list2);
        this.zzaTY = Collections.unmodifiableList((List<? extends zzqp$zza>)list3);
        this.zzaTZ = Collections.unmodifiableList((List<? extends zzqp$zza>)list4);
        this.zzaUa = Collections.unmodifiableList((List<? extends zzqp$zza>)list5);
        this.zzaUb = Collections.unmodifiableList((List<? extends zzqp$zza>)list6);
        this.zzaUc = Collections.unmodifiableList((List<? extends String>)list7);
        this.zzaUd = Collections.unmodifiableList((List<? extends String>)list8);
        this.zzaUe = Collections.unmodifiableList((List<? extends String>)list9);
        this.zzaUf = Collections.unmodifiableList((List<? extends String>)list10);
    }
    
    public static zzqp$zzf zzBJ() {
        return new zzqp$zzf(null);
    }
    
    @Override
    public String toString() {
        return "Positive predicates: " + this.zzBK() + "  Negative predicates: " + this.zzBL() + "  Add tags: " + this.zzBM() + "  Remove tags: " + this.zzBN() + "  Add macros: " + this.zzBO() + "  Remove macros: " + this.zzBT();
    }
    
    public List<zzqp$zza> zzBK() {
        return this.zzaTW;
    }
    
    public List<zzqp$zza> zzBL() {
        return this.zzaTX;
    }
    
    public List<zzqp$zza> zzBM() {
        return this.zzaTY;
    }
    
    public List<zzqp$zza> zzBN() {
        return this.zzaTZ;
    }
    
    public List<zzqp$zza> zzBO() {
        return this.zzaUa;
    }
    
    public List<String> zzBP() {
        return this.zzaUc;
    }
    
    public List<String> zzBQ() {
        return this.zzaUd;
    }
    
    public List<String> zzBR() {
        return this.zzaUe;
    }
    
    public List<String> zzBS() {
        return this.zzaUf;
    }
    
    public List<zzqp$zza> zzBT() {
        return this.zzaUb;
    }
}
