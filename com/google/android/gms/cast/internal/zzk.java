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
    public static final Api$zzc<zze> zzRk;
    public static final Charset zzZk;
    
    static {
        zzRk = new Api$zzc<zze>();
        Charset forName = null;
        while (true) {
            try {
                forName = Charset.forName("UTF-8");
                zzZk = forName;
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
