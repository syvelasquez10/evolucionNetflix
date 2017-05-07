// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.HashSet;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import android.content.Context;
import java.util.Set;

class zzj extends zzdd
{
    private static final String ID;
    private static final String URL;
    private static final String zzaOL;
    private static final String zzaOM;
    static final String zzaON;
    private static final Set<String> zzaOO;
    private final Context mContext;
    private final zzj$zza zzaOP;
    
    static {
        ID = zzad.zzco.toString();
        URL = zzae.zzhx.toString();
        zzaOL = zzae.zzdn.toString();
        zzaOM = zzae.zzhw.toString();
        zzaON = "gtm_" + zzj.ID + "_unrepeatable";
        zzaOO = new HashSet<String>();
    }
    
    public zzj(final Context context) {
        this(context, new zzj$1(context));
    }
    
    zzj(final Context mContext, final zzj$zza zzaOP) {
        super(zzj.ID, new String[] { zzj.URL });
        this.zzaOP = zzaOP;
        this.mContext = mContext;
    }
    
    private boolean zzeq(final String s) {
        while (true) {
            boolean b = true;
            synchronized (this) {
                if (!this.zzes(s)) {
                    if (!this.zzer(s)) {
                        return false;
                    }
                    zzj.zzaOO.add(s);
                }
                return b;
            }
            b = false;
            return b;
        }
    }
    
    @Override
    public void zzI(final Map<String, zzag$zza> map) {
        String zzg;
        if (map.get(zzj.zzaOM) != null) {
            zzg = zzdf.zzg(map.get(zzj.zzaOM));
        }
        else {
            zzg = null;
        }
        if (zzg == null || !this.zzeq(zzg)) {
            final Uri$Builder buildUpon = Uri.parse(zzdf.zzg(map.get(zzj.URL))).buildUpon();
            final zzag$zza zzag$zza = map.get(zzj.zzaOL);
            if (zzag$zza != null) {
                final Object zzl = zzdf.zzl(zzag$zza);
                if (!(zzl instanceof List)) {
                    zzbg.e("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (final Map<Object, V> next : (List<Object>)zzl) {
                    if (!(next instanceof Map)) {
                        zzbg.e("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (final Map.Entry<Object, V> entry : next.entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            final String string = buildUpon.build().toString();
            this.zzaOP.zzzy().zzeH(string);
            zzbg.v("ArbitraryPixel: url = " + string);
            if (zzg != null) {
                synchronized (zzj.class) {
                    zzj.zzaOO.add(zzg);
                    zzcv.zzb(this.mContext, zzj.zzaON, zzg, "true");
                }
            }
        }
    }
    
    boolean zzer(final String s) {
        return this.mContext.getSharedPreferences(zzj.zzaON, 0).contains(s);
    }
    
    boolean zzes(final String s) {
        return zzj.zzaOO.contains(s);
    }
}
