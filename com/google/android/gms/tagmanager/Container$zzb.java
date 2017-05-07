// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;

class Container$zzb implements zzt$zza
{
    final /* synthetic */ Container zzaOZ;
    
    private Container$zzb(final Container zzaOZ) {
        this.zzaOZ = zzaOZ;
    }
    
    @Override
    public Object zzd(final String s, final Map<String, Object> map) {
        final Container$FunctionCallTagCallback zzev = this.zzaOZ.zzev(s);
        if (zzev != null) {
            zzev.execute(s, map);
        }
        return zzdf.zzBf();
    }
}
