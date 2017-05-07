// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Collections;
import com.google.android.gms.common.internal.zzx;
import java.util.Map;
import java.util.List;

public class zzab
{
    private final List<Command> zzNY;
    private final long zzNZ;
    private final long zzOa;
    private final int zzOb;
    private final boolean zzOc;
    private final String zzOd;
    private final Map<String, String> zzvs;
    
    public zzab(final zzc zzc, final Map<String, String> map, final long n, final boolean b) {
        this(zzc, map, n, b, 0L, 0, null);
    }
    
    public zzab(final zzc zzc, final Map<String, String> map, final long n, final boolean b, final long n2, final int n3) {
        this(zzc, map, n, b, n2, n3, null);
    }
    
    public zzab(final zzc zzc, final Map<String, String> map, final long zzOa, final boolean zzOc, final long zzNZ, final int zzOb, final List<Command> list) {
        zzx.zzv(zzc);
        zzx.zzv(map);
        this.zzOa = zzOa;
        this.zzOc = zzOc;
        this.zzNZ = zzNZ;
        this.zzOb = zzOb;
        List<Command> empty_LIST;
        if (list != null) {
            empty_LIST = list;
        }
        else {
            empty_LIST = (List<Command>)Collections.EMPTY_LIST;
        }
        this.zzNY = empty_LIST;
        this.zzOd = zze(list);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            if (zzj(entry.getKey())) {
                final String zza = zza(zzc, entry.getKey());
                if (zza == null) {
                    continue;
                }
                hashMap.put(zza, zzb(zzc, entry.getValue()));
            }
        }
        for (final Map.Entry<String, String> entry2 : map.entrySet()) {
            if (!zzj(entry2.getKey())) {
                final String zza2 = zza(zzc, entry2.getKey());
                if (zza2 == null) {
                    continue;
                }
                hashMap.put(zza2, zzb(zzc, entry2.getValue()));
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzOd)) {
            zzam.zzb(hashMap, "_v", this.zzOd);
            if (this.zzOd.equals("ma4.0.0") || this.zzOd.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzvs = (Map<String, String>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static zzab zza(final zzc zzc, final zzab zzab, final Map<String, String> map) {
        return new zzab(zzc, map, zzab.zzkk(), zzab.zzkm(), zzab.zzkj(), zzab.zzki(), zzab.zzkl());
    }
    
    private static String zza(final zzc zzc, final Object o) {
        String s;
        if (o == null) {
            s = null;
        }
        else {
            String s3;
            final String s2 = s3 = o.toString();
            if (s2.startsWith("&")) {
                s3 = s2.substring(1);
            }
            final int length = s3.length();
            String substring = s3;
            if (length > 256) {
                substring = s3.substring(0, 256);
                zzc.zzc("Hit param name is too long and will be trimmed", length, substring);
            }
            s = substring;
            if (TextUtils.isEmpty((CharSequence)substring)) {
                return null;
            }
        }
        return s;
    }
    
    private static String zzb(final zzc zzc, final Object o) {
        String string;
        if (o == null) {
            string = "";
        }
        else {
            string = o.toString();
        }
        final int length = string.length();
        String substring = string;
        if (length > 8192) {
            substring = string.substring(0, 8192);
            zzc.zzc("Hit param value is too long and will be trimmed", length, substring);
        }
        return substring;
    }
    
    private static String zze(final List<Command> list) {
    Label_0047:
        while (true) {
            if (list != null) {
                for (final Command command : list) {
                    if ("appendVersion".equals(command.getId())) {
                        final String value = command.getValue();
                        break Label_0047;
                    }
                }
            }
            Label_0058: {
                break Label_0058;
                final String value;
                if (TextUtils.isEmpty((CharSequence)value)) {
                    return null;
                }
                return value;
            }
            final String value = null;
            continue Label_0047;
        }
    }
    
    private static boolean zzj(final Object o) {
        return o != null && o.toString().startsWith("&");
    }
    
    private String zzo(String s, final String s2) {
        zzx.zzcs(s);
        zzx.zzb(!s.startsWith("&"), "Short param name required");
        s = this.zzvs.get(s);
        if (s != null) {
            return s;
        }
        return s2;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ht=").append(this.zzOa);
        if (this.zzNZ != 0L) {
            sb.append(", dbId=").append(this.zzNZ);
        }
        if (this.zzOb != 0L) {
            sb.append(", appUID=").append(this.zzOb);
        }
        final ArrayList<String> list = new ArrayList<String>(this.zzvs.keySet());
        Collections.sort((List<Comparable>)list);
        for (final String s : list) {
            sb.append(", ");
            sb.append(s);
            sb.append("=");
            sb.append(this.zzvs.get(s));
        }
        return sb.toString();
    }
    
    public int zzki() {
        return this.zzOb;
    }
    
    public long zzkj() {
        return this.zzNZ;
    }
    
    public long zzkk() {
        return this.zzOa;
    }
    
    public List<Command> zzkl() {
        return this.zzNY;
    }
    
    public boolean zzkm() {
        return this.zzOc;
    }
    
    public long zzkn() {
        return zzam.zzbo(this.zzo("_s", "0"));
    }
    
    public String zzko() {
        return this.zzo("_m", "");
    }
    
    public Map<String, String> zzn() {
        return this.zzvs;
    }
}
