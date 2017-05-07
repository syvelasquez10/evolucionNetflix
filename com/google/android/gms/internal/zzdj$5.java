// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdj$5 implements zzdk
{
    @Override
    public void zza(final zziz zziz, Map<String, String> o) {
        final String s = ((Map<String, String>)o).get("u");
        if (s == null) {
            zzb.zzaH("URL missing from click GMSG.");
            return;
        }
        while (true) {
            o = Uri.parse(s);
            while (true) {
                Label_0120: {
                    try {
                        final zzan zzhg = zziz.zzhg();
                        if (zzhg != null && zzhg.zzb((Uri)o)) {
                            o = zzhg.zza((Uri)o, zziz.getContext());
                            o = ((Uri)o).toString();
                            new zzij(zziz.getContext(), zziz.zzhh().zzJu, (String)o).zzgz();
                            return;
                        }
                        break Label_0120;
                    }
                    catch (zzao zzao) {
                        zzb.zzaH("Unable to append parameter to URL: " + s);
                    }
                }
                continue;
            }
        }
    }
}
