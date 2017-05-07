// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.HashSet;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzay extends zzak
{
    private static final String ID;
    private static final String zzaPY;
    private static final String zzaQq;
    private static final String zzaQr;
    private static final String zzaQs;
    
    static {
        ID = zzad.zzbP.toString();
        zzaPY = zzae.zzdz.toString();
        zzaQq = zzae.zzfx.toString();
        zzaQr = zzae.zzfB.toString();
        zzaQs = zzae.zzeU.toString();
    }
    
    public zzay() {
        super(zzay.ID, new String[] { zzay.zzaPY });
    }
    
    private String zza(String s, final zzay$zza zzay$zza, final Set<Character> set) {
        switch (zzay$1.zzaQt[zzay$zza.ordinal()]) {
            default: {
                return s;
            }
            case 1: {
                try {
                    return zzdj.zzff(s);
                }
                catch (UnsupportedEncodingException ex) {
                    zzbg.zzb("Joiner: unsupported encoding", ex);
                    return s;
                }
            }
            case 2: {
                s = s.replace("\\", "\\\\");
                final Iterator<Character> iterator = set.iterator();
                while (iterator.hasNext()) {
                    final String string = iterator.next().toString();
                    s = s.replace(string, "\\" + string);
                }
                return s;
            }
        }
    }
    
    private void zza(final StringBuilder sb, final String s, final zzay$zza zzay$zza, final Set<Character> set) {
        sb.append(this.zza(s, zzay$zza, set));
    }
    
    private void zza(final Set<Character> set, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            set.add(s.charAt(i));
        }
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzay.zzaPY);
        if (zzag$zza == null) {
            return zzdf.zzBg();
        }
        final zzag$zza zzag$zza2 = map.get(zzay.zzaQq);
        String zzg;
        if (zzag$zza2 != null) {
            zzg = zzdf.zzg(zzag$zza2);
        }
        else {
            zzg = "";
        }
        final zzag$zza zzag$zza3 = map.get(zzay.zzaQr);
        String zzg2;
        if (zzag$zza3 != null) {
            zzg2 = zzdf.zzg(zzag$zza3);
        }
        else {
            zzg2 = "=";
        }
        zzay$zza zzay$zza = com.google.android.gms.tagmanager.zzay$zza.zzaQu;
        final zzag$zza zzag$zza4 = map.get(zzay.zzaQs);
        Set<Character> set;
        if (zzag$zza4 != null) {
            final String zzg3 = zzdf.zzg(zzag$zza4);
            if ("url".equals(zzg3)) {
                zzay$zza = com.google.android.gms.tagmanager.zzay$zza.zzaQv;
                set = null;
            }
            else {
                if (!"backslash".equals(zzg3)) {
                    zzbg.e("Joiner: unsupported escape type: " + zzg3);
                    return zzdf.zzBg();
                }
                zzay$zza = com.google.android.gms.tagmanager.zzay$zza.zzaQw;
                set = new HashSet<Character>();
                this.zza(set, zzg);
                this.zza(set, zzg2);
                set.remove('\\');
            }
        }
        else {
            set = null;
        }
        final StringBuilder sb = new StringBuilder();
        switch (zzag$zza.type) {
            default: {
                this.zza(sb, zzdf.zzg(zzag$zza), zzay$zza, set);
                break;
            }
            case 2: {
                int n = 1;
                final zzag$zza[] zziV = zzag$zza.zziV;
                for (int length = zziV.length, i = 0; i < length; ++i, n = 0) {
                    final zzag$zza zzag$zza5 = zziV[i];
                    if (n == 0) {
                        sb.append(zzg);
                    }
                    this.zza(sb, zzdf.zzg(zzag$zza5), zzay$zza, set);
                }
                break;
            }
            case 3: {
                for (int j = 0; j < zzag$zza.zziW.length; ++j) {
                    if (j > 0) {
                        sb.append(zzg);
                    }
                    final String zzg4 = zzdf.zzg(zzag$zza.zziW[j]);
                    final String zzg5 = zzdf.zzg(zzag$zza.zziX[j]);
                    this.zza(sb, zzg4, zzay$zza, set);
                    sb.append(zzg2);
                    this.zza(sb, zzg5, zzay$zza, set);
                }
                break;
            }
        }
        return zzdf.zzK(sb.toString());
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
