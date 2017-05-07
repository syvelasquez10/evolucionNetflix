// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.security.NoSuchAlgorithmException;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.security.MessageDigest;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzap extends zzak
{
    private static final String ID;
    private static final String zzaPY;
    private static final String zzaQa;
    private static final String zzaQe;
    
    static {
        ID = zzad.zzbN.toString();
        zzaPY = zzae.zzdz.toString();
        zzaQe = zzae.zzdq.toString();
        zzaQa = zzae.zzft.toString();
    }
    
    public zzap() {
        super(zzap.ID, new String[] { zzap.zzaPY });
    }
    
    private byte[] zzd(final String s, final byte[] array) {
        final MessageDigest instance = MessageDigest.getInstance(s);
        instance.update(array);
        return instance.digest();
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzap.zzaPY);
        if (zzag$zza == null || zzag$zza == zzdf.zzBg()) {
            return zzdf.zzBg();
        }
        final String zzg = zzdf.zzg(zzag$zza);
        Object zzg2 = map.get(zzap.zzaQe);
        Label_0102: {
            if (zzg2 != null) {
                break Label_0102;
            }
            zzg2 = "MD5";
        Label_0073_Outer:
            while (true) {
                final zzag$zza zzag$zza2 = map.get(zzap.zzaQa);
                Label_0110: {
                    if (zzag$zza2 != null) {
                        break Label_0110;
                    }
                    String zzg3 = "text";
                    while (true) {
                        Label_0118: {
                            if (!"text".equals(zzg3)) {
                                break Label_0118;
                            }
                            byte[] array = zzg.getBytes();
                            try {
                                return zzdf.zzK(zzk.zzi(this.zzd((String)zzg2, array)));
                                // iftrue(Label_0135:, !"base16".equals((Object)zzg3))
                                Block_6: {
                                    break Block_6;
                                    Label_0135: {
                                        zzbg.e("Hash: unknown input format: " + zzg3);
                                    }
                                    return zzdf.zzBg();
                                    zzg2 = zzdf.zzg((zzag$zza)zzg2);
                                    continue Label_0073_Outer;
                                    zzg3 = zzdf.zzg(zzag$zza2);
                                    continue;
                                }
                                array = zzk.zzet(zzg);
                                return zzdf.zzK(zzk.zzi(this.zzd((String)zzg2, array)));
                            }
                            catch (NoSuchAlgorithmException ex) {
                                zzbg.e("Hash: unknown algorithm: " + (String)zzg2);
                                return zzdf.zzBg();
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
