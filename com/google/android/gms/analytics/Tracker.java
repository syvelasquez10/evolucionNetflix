// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzu;
import com.google.android.gms.analytics.internal.zzk;
import com.google.android.gms.analytics.internal.zza;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzx;
import java.util.Random;
import java.util.HashMap;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzad;
import java.util.Map;
import com.google.android.gms.analytics.internal.zzd;

public class Tracker extends zzd
{
    private boolean zzKH;
    private final Map<String, String> zzKI;
    private final zzad zzKJ;
    private final Tracker$zza zzKK;
    private final Map<String, String> zzvs;
    
    Tracker(final zzf zzf, final String s, final zzad zzKJ) {
        super(zzf);
        this.zzvs = new HashMap<String, String>();
        this.zzKI = new HashMap<String, String>();
        if (s != null) {
            this.zzvs.put("&tid", s);
        }
        this.zzvs.put("useSecure", "1");
        this.zzvs.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        if (zzKJ == null) {
            this.zzKJ = new zzad("tracking");
        }
        else {
            this.zzKJ = zzKJ;
        }
        this.zzKK = new Tracker$zza(this, zzf);
    }
    
    private static void zza(final Map<String, String> map, final Map<String, String> map2) {
        zzx.zzv(map2);
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String zzb = zzb(entry);
                if (zzb != null) {
                    map2.put(zzb, entry.getValue());
                }
            }
        }
    }
    
    private static boolean zza(final Map.Entry<String, String> entry) {
        final String s = entry.getKey();
        final String s2 = entry.getValue();
        return s.startsWith("&") && s.length() >= 2;
    }
    
    private static String zzb(final Map.Entry<String, String> entry) {
        if (!zza(entry)) {
            return null;
        }
        return entry.getKey().substring(1);
    }
    
    private static void zzb(final Map<String, String> map, final Map<String, String> map2) {
        zzx.zzv(map2);
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String zzb = zzb(entry);
                if (zzb != null && !map2.containsKey(zzb)) {
                    map2.put(zzb, entry.getValue());
                }
            }
        }
    }
    
    public void enableAdvertisingIdCollection(final boolean zzKH) {
        this.zzKH = zzKH;
    }
    
    public void send(final Map<String, String> map) {
        final long currentTimeMillis = this.zzid().currentTimeMillis();
        if (this.zzhu().getAppOptOut()) {
            this.zzaZ("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        final boolean dryRunEnabled = this.zzhu().isDryRunEnabled();
        final HashMap<Object, String> hashMap = new HashMap<Object, String>();
        zza(this.zzvs, (Map<String, String>)hashMap);
        zza(map, (Map<String, String>)hashMap);
        final boolean zze = zzam.zze(this.zzvs.get("useSecure"), true);
        zzb(this.zzKI, (Map<String, String>)hashMap);
        this.zzKI.clear();
        final String s = hashMap.get("t");
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzie().zzh((Map<String, String>)hashMap, "Missing hit type parameter");
            return;
        }
        final String s2 = hashMap.get("tid");
        if (TextUtils.isEmpty((CharSequence)s2)) {
            this.zzie().zzh((Map<String, String>)hashMap, "Missing tracking id parameter");
            return;
        }
        final boolean zzhD = this.zzhD();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(s) || "pageview".equalsIgnoreCase(s) || "appview".equalsIgnoreCase(s) || TextUtils.isEmpty((CharSequence)s)) {
                int n;
                if ((n = Integer.parseInt(this.zzvs.get("&a")) + 1) >= Integer.MAX_VALUE) {
                    n = 1;
                }
                this.zzvs.put("&a", Integer.toString(n));
            }
            // monitorexit(this)
            this.zzig().zzf(new Tracker$1(this, hashMap, zzhD, s, currentTimeMillis, dryRunEnabled, zze, s2));
        }
    }
    
    public void set(final String s, final String s2) {
        zzx.zzb(s, "Key should be non-null");
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        this.zzvs.put(s, s2);
    }
    
    public void setScreenName(final String s) {
        this.set("&cd", s);
    }
    
    @Override
    protected void zzhB() {
        this.zzKK.zza();
        final String zzjZ = this.zzhA().zzjZ();
        if (zzjZ != null) {
            this.set("&an", zzjZ);
        }
        final String zzkb = this.zzhA().zzkb();
        if (zzkb != null) {
            this.set("&av", zzkb);
        }
    }
    
    boolean zzhD() {
        return this.zzKH;
    }
}
