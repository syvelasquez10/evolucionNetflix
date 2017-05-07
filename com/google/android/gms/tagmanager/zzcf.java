// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzcf extends zzcz
{
    private static final String ID;
    private static final String zzaRk;
    
    static {
        ID = zzad.zzce.toString();
        zzaRk = zzae.zzfr.toString();
    }
    
    public zzcf() {
        super(zzcf.ID);
    }
    
    @Override
    protected boolean zza(final String s, final String s2, final Map<String, zzag$zza> map) {
        while (true) {
            Label_0046: {
                if (!zzdf.zzk(map.get(zzcf.zzaRk))) {
                    break Label_0046;
                }
                final int n = 66;
                try {
                    return Pattern.compile(s2, n).matcher(s).find();
                }
                catch (PatternSyntaxException ex) {
                    return false;
                }
            }
            final int n = 64;
            continue;
        }
    }
}
