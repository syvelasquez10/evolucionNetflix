// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.charset.Charset;
import com.google.android.gms.common.api.Api$zzc;

public final class zzk
{
    public static final Api$zzc<zze> zzQf;
    public static final String zzXs;
    public static final String zzXt;
    public static final Charset zzXu;
    
    static {
        zzQf = new Api$zzc<zze>();
        zzXs = zzf.zzbM("com.google.cast.receiver");
        zzXt = zzf.zzbM("com.google.cast.tp.connection");
        Charset forName = null;
        while (true) {
            try {
                forName = Charset.forName("UTF-8");
                zzXu = forName;
            }
            catch (UnsupportedCharsetException ex) {
                continue;
            }
            catch (IllegalCharsetNameException ex2) {
                continue;
            }
            break;
        }
    }
}
