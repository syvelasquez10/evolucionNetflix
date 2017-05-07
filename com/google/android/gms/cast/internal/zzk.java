// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.charset.Charset;
import com.google.android.gms.common.api.Api$ClientKey;

public final class zzk
{
    public static final Api$ClientKey<zze> zzNX;
    public static final String zzUP;
    public static final String zzUQ;
    public static final Charset zzUR;
    
    static {
        zzNX = new Api$ClientKey<zze>();
        zzUP = zzf.zzbE("com.google.cast.receiver");
        zzUQ = zzf.zzbE("com.google.cast.tp.connection");
        Charset forName = null;
        while (true) {
            try {
                forName = Charset.forName("UTF-8");
                zzUR = forName;
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
