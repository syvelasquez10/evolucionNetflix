// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Iterator;
import com.google.android.gms.internal.zzok;
import com.google.android.gms.internal.zzom;
import java.util.List;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzos;
import com.google.android.gms.internal.zzor;
import com.google.android.gms.internal.zzop;
import com.google.android.gms.internal.zzol;
import com.google.android.gms.internal.zzoo;
import com.google.android.gms.internal.zzoq;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzja;
import java.util.HashMap;
import com.google.android.gms.internal.zzob;
import android.net.Uri$Builder;
import android.text.TextUtils;
import java.util.Map;
import com.google.android.gms.common.internal.zzx;
import android.net.Uri;
import com.google.android.gms.analytics.internal.zzf;
import java.text.DecimalFormat;
import com.google.android.gms.internal.zzoh;
import com.google.android.gms.analytics.internal.zzc;

public class zzb extends zzc implements zzoh
{
    private static DecimalFormat zzKk;
    private final zzf zzKa;
    private final Uri zzKl;
    private final boolean zzKm;
    private final boolean zzKn;
    private final String zztw;
    
    public zzb(final zzf zzf, final String s) {
        this(zzf, s, true, false);
    }
    
    public zzb(final zzf zzKa, final String zztw, final boolean zzKm, final boolean zzKn) {
        super(zzKa);
        zzx.zzcs(zztw);
        this.zzKa = zzKa;
        this.zztw = zztw;
        this.zzKm = zzKm;
        this.zzKn = zzKn;
        this.zzKl = zzaP(this.zztw);
    }
    
    private static void zza(final Map<String, String> map, final String s, final double n) {
        if (n != 0.0) {
            map.put(s, zzb(n));
        }
    }
    
    private static void zza(final Map<String, String> map, final String s, final int n, final int n2) {
        if (n > 0 && n2 > 0) {
            map.put(s, n + "x" + n2);
        }
    }
    
    private static void zza(final Map<String, String> map, final String s, final String s2) {
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            map.put(s, s2);
        }
    }
    
    private static void zza(final Map<String, String> map, final String s, final boolean b) {
        if (b) {
            map.put(s, "1");
        }
    }
    
    static Uri zzaP(final String s) {
        zzx.zzcs(s);
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("uri");
        uri$Builder.authority("google-analytics.com");
        uri$Builder.path(s);
        return uri$Builder.build();
    }
    
    static String zzb(final double n) {
        if (zzb.zzKk == null) {
            zzb.zzKk = new DecimalFormat("0.######");
        }
        return zzb.zzKk.format(n);
    }
    
    public static Map<String, String> zzc(final zzob zzob) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final zzja zzja = zzob.zzd(zzja.class);
        if (zzja != null) {
            for (final Map.Entry<String, Object> entry : zzja.zzhJ().entrySet()) {
                final String zzh = zzh(entry.getValue());
                if (zzh != null) {
                    hashMap.put(entry.getKey(), zzh);
                }
            }
        }
        final zzjb zzjb = zzob.zzd(zzjb.class);
        if (zzjb != null) {
            zza(hashMap, "t", zzjb.zzhK());
            zza(hashMap, "cid", zzjb.getClientId());
            zza(hashMap, "uid", zzjb.getUserId());
            zza(hashMap, "sc", zzjb.zzhN());
            zza(hashMap, "sf", zzjb.zzhP());
            zza(hashMap, "ni", zzjb.zzhO());
            zza(hashMap, "adid", zzjb.zzhL());
            zza(hashMap, "ate", zzjb.zzhM());
        }
        final zzoq zzoq = zzob.zzd(zzoq.class);
        if (zzoq != null) {
            zza(hashMap, "cd", zzoq.zzxT());
            zza(hashMap, "a", zzoq.zzbp());
            zza(hashMap, "dr", zzoq.zzxW());
        }
        final zzoo zzoo = zzob.zzd(zzoo.class);
        if (zzoo != null) {
            zza(hashMap, "ec", zzoo.zzxQ());
            zza(hashMap, "ea", zzoo.getAction());
            zza(hashMap, "el", zzoo.getLabel());
            zza(hashMap, "ev", zzoo.getValue());
        }
        final zzol zzol = zzob.zzd(zzol.class);
        if (zzol != null) {
            zza(hashMap, "cn", zzol.getName());
            zza(hashMap, "cs", zzol.getSource());
            zza(hashMap, "cm", zzol.zzxB());
            zza(hashMap, "ck", zzol.zzxC());
            zza(hashMap, "cc", zzol.getContent());
            zza(hashMap, "ci", zzol.getId());
            zza(hashMap, "anid", zzol.zzxD());
            zza(hashMap, "gclid", zzol.zzxE());
            zza(hashMap, "dclid", zzol.zzxF());
            zza(hashMap, "aclid", zzol.zzxG());
        }
        final zzop zzop = zzob.zzd(zzop.class);
        if (zzop != null) {
            zza(hashMap, "exd", zzop.getDescription());
            zza(hashMap, "exf", zzop.zzxR());
        }
        final zzor zzor = zzob.zzd(zzor.class);
        if (zzor != null) {
            zza(hashMap, "sn", zzor.zzya());
            zza(hashMap, "sa", zzor.getAction());
            zza(hashMap, "st", zzor.getTarget());
        }
        final zzos zzos = zzob.zzd(zzos.class);
        if (zzos != null) {
            zza(hashMap, "utv", zzos.zzyb());
            zza(hashMap, "utt", zzos.getTimeInMillis());
            zza(hashMap, "utc", zzos.zzxQ());
            zza(hashMap, "utl", zzos.getLabel());
        }
        final zziy zziy = zzob.zzd(zziy.class);
        if (zziy != null) {
            for (final Map.Entry<Integer, String> entry2 : zziy.zzhH().entrySet()) {
                final String zzP = com.google.android.gms.analytics.zzc.zzP(entry2.getKey());
                if (!TextUtils.isEmpty((CharSequence)zzP)) {
                    hashMap.put(zzP, entry2.getValue());
                }
            }
        }
        final zziz zziz = zzob.zzd(zziz.class);
        if (zziz != null) {
            for (final Map.Entry<Integer, Double> entry3 : zziz.zzhI().entrySet()) {
                final String zzR = com.google.android.gms.analytics.zzc.zzR(entry3.getKey());
                if (!TextUtils.isEmpty((CharSequence)zzR)) {
                    hashMap.put(zzR, zzb(entry3.getValue()));
                }
            }
        }
        final zzon zzon = zzob.zzd(zzon.class);
        if (zzon != null) {
            final ProductAction zzxM = zzon.zzxM();
            if (zzxM != null) {
                for (final Map.Entry<String, String> entry4 : zzxM.build().entrySet()) {
                    if (entry4.getKey().startsWith("&")) {
                        hashMap.put(entry4.getKey().substring(1), entry4.getValue());
                    }
                    else {
                        hashMap.put(entry4.getKey(), entry4.getValue());
                    }
                }
            }
            final Iterator<Promotion> iterator5 = zzon.zzxP().iterator();
            int n = 1;
            while (iterator5.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator5.next().zzaV(com.google.android.gms.analytics.zzc.zzV(n)));
                ++n;
            }
            final Iterator<Product> iterator6 = zzon.zzxN().iterator();
            int n2 = 1;
            while (iterator6.hasNext()) {
                hashMap.putAll((Map<?, ?>)iterator6.next().zzaV(com.google.android.gms.analytics.zzc.zzT(n2)));
                ++n2;
            }
            final Iterator<Map.Entry<String, List<Product>>> iterator7 = zzon.zzxO().entrySet().iterator();
            int n3 = 1;
            while (iterator7.hasNext()) {
                final Map.Entry<String, List<Product>> entry5 = iterator7.next();
                final List<Product> list = entry5.getValue();
                final String zzY = com.google.android.gms.analytics.zzc.zzY(n3);
                final Iterator<Product> iterator8 = list.iterator();
                int n4 = 1;
                while (iterator8.hasNext()) {
                    hashMap.putAll((Map<?, ?>)iterator8.next().zzaV(zzY + com.google.android.gms.analytics.zzc.zzW(n4)));
                    ++n4;
                }
                if (!TextUtils.isEmpty((CharSequence)entry5.getKey())) {
                    hashMap.put(zzY + "nm", entry5.getKey());
                }
                ++n3;
            }
        }
        final zzom zzom = zzob.zzd(zzom.class);
        if (zzom != null) {
            zza(hashMap, "ul", zzom.getLanguage());
            zza(hashMap, "sd", zzom.zzxH());
            zza(hashMap, "sr", zzom.zzxI(), zzom.zzxJ());
            zza(hashMap, "vp", zzom.zzxK(), zzom.zzxL());
        }
        final zzok zzok = zzob.zzd(zzok.class);
        if (zzok != null) {
            zza(hashMap, "an", zzok.zzjZ());
            zza(hashMap, "aid", zzok.zztW());
            zza(hashMap, "aiid", zzok.zzxA());
            zza(hashMap, "av", zzok.zzkb());
        }
        return hashMap;
    }
    
    private static String zzh(final Object o) {
        String s;
        if (o == null) {
            s = null;
        }
        else if (o instanceof String) {
            if (TextUtils.isEmpty((CharSequence)(s = (String)o))) {
                return null;
            }
        }
        else if (o instanceof Double) {
            final Double n = (Double)o;
            if (n != 0.0) {
                return zzb(n);
            }
            return null;
        }
        else {
            if (!(o instanceof Boolean)) {
                return String.valueOf(o);
            }
            if (o != Boolean.FALSE) {
                return "1";
            }
            return null;
        }
        return s;
    }
    
    private static String zzy(final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
    
    @Override
    public void zzb(final zzob zzob) {
        zzx.zzv(zzob);
        zzx.zzb(zzob.zzxm(), "Can't deliver not submitted measurement");
        zzx.zzci("deliver should be called on worker thread");
        final zzob zzxh = zzob.zzxh();
        final zzjb zzjb = zzxh.zze(zzjb.class);
        if (TextUtils.isEmpty((CharSequence)zzjb.zzhK())) {
            this.zzie().zzh(zzc(zzxh), "Ignoring measurement without type");
        }
        else {
            if (TextUtils.isEmpty((CharSequence)zzjb.getClientId())) {
                this.zzie().zzh(zzc(zzxh), "Ignoring measurement without client id");
                return;
            }
            if (!this.zzKa.zzis().getAppOptOut()) {
                final double zzhP = zzjb.zzhP();
                if (zzam.zza(zzhP, zzjb.getClientId())) {
                    this.zzb("Sampling enabled. Hit sampled out. sampling rate", zzhP);
                    return;
                }
                final Map<String, String> zzc = zzc(zzxh);
                zzc.put("v", "1");
                zzc.put("_v", zze.zzLB);
                zzc.put("tid", this.zztw);
                if (this.zzKa.zzis().isDryRunEnabled()) {
                    this.zzc("Dry run is enabled. GoogleAnalytics would have sent", zzy(zzc));
                    return;
                }
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                zzam.zzb(hashMap, "uid", zzjb.getUserId());
                final zzok zzok = zzob.zzd(zzok.class);
                if (zzok != null) {
                    zzam.zzb(hashMap, "an", zzok.zzjZ());
                    zzam.zzb(hashMap, "aid", zzok.zztW());
                    zzam.zzb(hashMap, "av", zzok.zzkb());
                    zzam.zzb(hashMap, "aiid", zzok.zzxA());
                }
                zzc.put("_s", String.valueOf(this.zzhz().zza(new zzh(0L, zzjb.getClientId(), this.zztw, !TextUtils.isEmpty((CharSequence)zzjb.zzhL()), 0L, hashMap))));
                this.zzhz().zza(new zzab(this.zzie(), zzc, zzob.zzxk(), true));
            }
        }
    }
    
    @Override
    public Uri zzhs() {
        return this.zzKl;
    }
}
