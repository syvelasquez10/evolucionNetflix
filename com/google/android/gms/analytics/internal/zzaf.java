// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.internal.zzx;
import android.util.Log;

public class zzaf extends zzd
{
    private static String zzOl;
    private static String zzOm;
    private static zzaf zzOn;
    
    static {
        zzaf.zzOl = "3";
        zzaf.zzOm = "01VDIWEA?";
    }
    
    public zzaf(final zzf zzf) {
        super(zzf);
    }
    
    public static zzaf zzkq() {
        return zzaf.zzOn;
    }
    
    public void zza(final int n, final String s, final Object o, final Object o2, final Object o3) {
        final String s2 = zzy.zzNa.get();
        if (Log.isLoggable(s2, n)) {
            Log.println(n, s2, zzc.zzc(s, o, o2, o3));
        }
        if (n >= 5) {
            this.zzb(n, s, o, o2, o3);
        }
    }
    
    public void zza(final zzab zzab, final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "no reason provided";
        }
        String string;
        if (zzab != null) {
            string = zzab.toString();
        }
        else {
            string = "no hit data";
        }
        this.zzd("Discarding hit. " + s2, string);
    }
    
    public void zzb(int n, String s, Object zzit, final Object o, final Object o2) {
        while (true) {
            final int n2 = 0;
            while (true) {
                Label_0214: {
                    while (true) {
                        Label_0211: {
                            synchronized (this) {
                                zzx.zzv(s);
                                if (n >= 0) {
                                    break Label_0214;
                                }
                                n = n2;
                                if (n >= zzaf.zzOm.length()) {
                                    n = zzaf.zzOm.length() - 1;
                                    char c;
                                    if (this.zzif().zzjl()) {
                                        if (this.zzif().zzjk()) {
                                            c = 'P';
                                        }
                                        else {
                                            c = 'C';
                                        }
                                    }
                                    else if (this.zzif().zzjk()) {
                                        c = 'p';
                                    }
                                    else {
                                        c = 'c';
                                    }
                                    zzit = (s = zzaf.zzOl + zzaf.zzOm.charAt(n) + c + zze.VERSION + ":" + zzc.zzc((String)s, this.zzk(zzit), this.zzk(o), this.zzk(o2)));
                                    if (((String)zzit).length() > 1024) {
                                        s = ((String)zzit).substring(0, 1024);
                                    }
                                    zzit = this.zzia().zzit();
                                    if (zzit != null) {
                                        ((zzai)zzit).zzkD().zzbl((String)s);
                                    }
                                    return;
                                }
                                break Label_0211;
                            }
                        }
                        continue;
                    }
                }
                continue;
            }
        }
    }
    
    public void zzh(final Map<String, String> map, final String s) {
        String s2 = s;
        if (s == null) {
            s2 = "no reason provided";
        }
        String string;
        if (map != null) {
            final StringBuilder sb = new StringBuilder();
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            string = sb.toString();
        }
        else {
            string = "no hit data";
        }
        this.zzd("Discarding hit. " + s2, string);
    }
    
    @Override
    protected void zzhB() {
        synchronized (zzaf.class) {
            zzaf.zzOn = this;
        }
    }
    
    protected String zzk(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Integer) {
            o = new Long((int)o);
        }
        if (o instanceof Long) {
            if (Math.abs((long)o) < 100L) {
                return String.valueOf(o);
            }
            String s;
            if (String.valueOf(o).charAt(0) == '-') {
                s = "-";
            }
            else {
                s = "";
            }
            final String value = String.valueOf(Math.abs((long)o));
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(Math.round(Math.pow(10.0, value.length() - 1)));
            sb.append("...");
            sb.append(s);
            sb.append(Math.round(Math.pow(10.0, value.length()) - 1.0));
            return sb.toString();
        }
        else {
            if (o instanceof Boolean) {
                return String.valueOf(o);
            }
            if (o instanceof Throwable) {
                return o.getClass().getCanonicalName();
            }
            return "-";
        }
    }
}
