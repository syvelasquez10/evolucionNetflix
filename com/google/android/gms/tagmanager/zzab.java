// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzab extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzbq.toString();
    }
    
    public zzab() {
        super(zzab.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final String manufacturer = Build.MANUFACTURER;
        String s2;
        final String s = s2 = Build.MODEL;
        if (!s.startsWith(manufacturer)) {
            s2 = s;
            if (!manufacturer.equals("unknown")) {
                s2 = manufacturer + " " + s;
            }
        }
        return zzdf.zzK(s2);
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
