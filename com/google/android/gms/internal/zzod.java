// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.List;
import android.text.TextUtils;
import java.util.Map;

public abstract class zzod<T extends zzod>
{
    public static String zzA(final Object o) {
        return zza(o, 0);
    }
    
    public static String zzF(final Map map) {
        return zza(map, 1);
    }
    
    private static String zza(final Object o, final int n) {
        if (n > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (o == null) {
            return "";
        }
        if (o instanceof String) {
            if (TextUtils.isEmpty((CharSequence)o)) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Integer) {
            if ((int)o == 0) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Long) {
            if ((long)o == 0L) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Double) {
            if ((double)o == 0.0) {
                return "";
            }
            return o.toString();
        }
        else if (o instanceof Boolean) {
            if (!(boolean)o) {
                return "";
            }
            return o.toString();
        }
        else {
            if (o instanceof List) {
                final StringBuffer sb = new StringBuffer();
                if (n > 0) {
                    sb.append("[");
                }
                final List list = (List)o;
                final int length = sb.length();
                for (final Object next : list) {
                    if (sb.length() > length) {
                        sb.append(", ");
                    }
                    sb.append(zza(next, n + 1));
                }
                if (n > 0) {
                    sb.append("]");
                }
                return sb.toString();
            }
            if (o instanceof Map) {
                final StringBuffer sb2 = new StringBuffer();
                final Iterator<Map.Entry<Object, Object>> iterator2 = new TreeMap<Object, Object>((Map<?, ?>)o).entrySet().iterator();
                int n2 = 0;
                int n3 = 0;
                while (iterator2.hasNext()) {
                    final Map.Entry<Object, Object> entry = iterator2.next();
                    final String zza = zza(entry.getValue(), n + 1);
                    if (!TextUtils.isEmpty((CharSequence)zza)) {
                        int length2 = n2;
                        int n4 = n3;
                        if (n > 0) {
                            length2 = n2;
                            if ((n4 = n3) == 0) {
                                sb2.append("{");
                                n4 = 1;
                                length2 = sb2.length();
                            }
                        }
                        if (sb2.length() > length2) {
                            sb2.append(", ");
                        }
                        sb2.append(entry.getKey());
                        sb2.append('=');
                        sb2.append(zza);
                        n2 = length2;
                        n3 = n4;
                    }
                }
                if (n3 != 0) {
                    sb2.append("}");
                }
                return sb2.toString();
            }
            return o.toString();
        }
    }
    
    public abstract void zza(final T p0);
}
