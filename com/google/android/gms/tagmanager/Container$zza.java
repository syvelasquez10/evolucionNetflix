// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;

class Container$zza implements zzt$zza
{
    final /* synthetic */ Container zzaOZ;
    
    private Container$zza(final Container zzaOZ) {
        this.zzaOZ = zzaOZ;
    }
    
    @Override
    public Object zzd(final String s, final Map<String, Object> map) {
        final Container$FunctionCallMacroCallback zzeu = this.zzaOZ.zzeu(s);
        if (zzeu == null) {
            return null;
        }
        return zzeu.getValue(s, map);
    }
}
