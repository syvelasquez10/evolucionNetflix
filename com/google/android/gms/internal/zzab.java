// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzab extends zzk<String>
{
    private final zzm$zzb<String> zzaG;
    
    public zzab(final int n, final String s, final zzm$zzb<String> zzaG, final zzm$zza zzm$zza) {
        super(n, s, zzm$zza);
        this.zzaG = zzaG;
    }
    
    public zzab(final String s, final zzm$zzb<String> zzm$zzb, final zzm$zza zzm$zza) {
        this(0, s, zzm$zzb, zzm$zza);
    }
    
    @Override
    protected zzm<String> zza(final zzi zzi) {
        try {
            final String s = new String(zzi.data, zzx.zza(zzi.zzA));
            return zzm.zza(s, zzx.zzb(zzi));
        }
        catch (UnsupportedEncodingException ex) {
            final String s = new String(zzi.data);
            return zzm.zza(s, zzx.zzb(zzi));
        }
    }
    
    protected void zzi(final String s) {
        this.zzaG.zzb(s);
    }
}
