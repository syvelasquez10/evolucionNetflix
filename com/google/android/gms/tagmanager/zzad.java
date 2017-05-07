// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;

class zzad extends zzak
{
    private static final String ID;
    private static final String zzaPY;
    private static final String zzaPZ;
    private static final String zzaQa;
    private static final String zzaQb;
    
    static {
        ID = com.google.android.gms.internal.zzad.zzbL.toString();
        zzaPY = zzae.zzdz.toString();
        zzaPZ = zzae.zzfX.toString();
        zzaQa = zzae.zzft.toString();
        zzaQb = zzae.zzgf.toString();
    }
    
    public zzad() {
        super(zzad.ID, new String[] { zzad.zzaPY });
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzad.zzaPY);
        if (zzag$zza == null || zzag$zza == zzdf.zzBg()) {
            return zzdf.zzBg();
        }
        final String zzg = zzdf.zzg(zzag$zza);
        final zzag$zza zzag$zza2 = map.get(zzad.zzaQa);
        while (true) {
        Label_0148_Outer:
            while (true) {
                Label_0075: {
                    while (true) {
                        Label_0055: {
                            if (zzag$zza2 == null) {
                                final String zzg2 = "text";
                                break Label_0055;
                            }
                            String zzg3 = null;
                            Label_0139: {
                                break Label_0139;
                                while (true) {
                                    while (true) {
                                        byte[] array = null;
                                        final int n;
                                        Label_0257: {
                                            try {
                                                final String zzg2;
                                                if ("text".equals(zzg2)) {
                                                    array = zzg.getBytes();
                                                }
                                                else if ("base16".equals(zzg2)) {
                                                    array = zzk.zzet(zzg);
                                                }
                                                else if ("base64".equals(zzg2)) {
                                                    array = Base64.decode(zzg, n);
                                                }
                                                else {
                                                    if (!"base64url".equals(zzg2)) {
                                                        zzbg.e("Encode: unknown input format: " + zzg2);
                                                        return zzdf.zzBg();
                                                    }
                                                    array = Base64.decode(zzg, n | 0x8);
                                                }
                                                if ("base16".equals(zzg3)) {
                                                    final String s = zzk.zzi(array);
                                                    return zzdf.zzK(s);
                                                }
                                                break Label_0257;
                                                final zzag$zza zzag$zza3;
                                                zzg3 = zzdf.zzg(zzag$zza3);
                                                break Label_0075;
                                                zzg2 = zzdf.zzg(zzag$zza2);
                                                break Label_0055;
                                            }
                                            catch (IllegalArgumentException ex) {
                                                zzbg.e("Encode: invalid input:");
                                                return zzdf.zzBg();
                                            }
                                        }
                                        if ("base64".equals(zzg3)) {
                                            final String s = Base64.encodeToString(array, n);
                                            continue Label_0148_Outer;
                                        }
                                        if ("base64url".equals(zzg3)) {
                                            final String s = Base64.encodeToString(array, n | 0x8);
                                            continue Label_0148_Outer;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            zzbg.e("Encode: unknown output format: " + zzg3);
                            return zzdf.zzBg();
                        }
                        final zzag$zza zzag$zza3 = map.get(zzad.zzaQb);
                        if (zzag$zza3 != null) {
                            continue;
                        }
                        break;
                    }
                    String zzg3 = "base16";
                }
                final zzag$zza zzag$zza4 = map.get(zzad.zzaPZ);
                if (zzag$zza4 != null && zzdf.zzk(zzag$zza4)) {
                    final int n = 3;
                    continue Label_0148_Outer;
                }
                break;
            }
            final int n = 2;
            continue;
        }
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
