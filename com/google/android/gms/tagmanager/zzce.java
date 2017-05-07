// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzce extends zzak
{
    private static final String ID;
    private static final String zzaRi;
    private static final String zzaRj;
    private static final String zzaRk;
    private static final String zzaRl;
    
    static {
        ID = zzad.zzbR.toString();
        zzaRi = zzae.zzdz.toString();
        zzaRj = zzae.zzdA.toString();
        zzaRk = zzae.zzfr.toString();
        zzaRl = zzae.zzfl.toString();
    }
    
    public zzce() {
        super(zzce.ID, new String[] { zzce.zzaRi, zzce.zzaRj });
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzce.zzaRi);
        final zzag$zza zzag$zza2 = map.get(zzce.zzaRj);
        if (zzag$zza == null || zzag$zza == zzdf.zzBg() || zzag$zza2 == null || zzag$zza2 == zzdf.zzBg()) {
            return zzdf.zzBg();
        }
        int n = 64;
        if (zzdf.zzk(map.get(zzce.zzaRk))) {
            n = 66;
        }
        final zzag$zza zzag$zza3 = map.get(zzce.zzaRl);
        int intValue;
        if (zzag$zza3 != null) {
            final Long zzi = zzdf.zzi(zzag$zza3);
            if (zzi == zzdf.zzBb()) {
                return zzdf.zzBg();
            }
            if ((intValue = (int)(Object)zzi) < 0) {
                return zzdf.zzBg();
            }
        }
        else {
            intValue = 1;
        }
        try {
            final String zzg = zzdf.zzg(zzag$zza);
            final String zzg2 = zzdf.zzg(zzag$zza2);
            final Object o = null;
            final Matcher matcher = Pattern.compile(zzg2, n).matcher(zzg);
            Object group = o;
            if (matcher.find()) {
                group = o;
                if (matcher.groupCount() >= intValue) {
                    group = matcher.group(intValue);
                }
            }
            if (group == null) {
                return zzdf.zzBg();
            }
            return zzdf.zzK(group);
        }
        catch (PatternSyntaxException ex) {
            return zzdf.zzBg();
        }
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
