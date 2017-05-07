// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Locale;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.Set;
import com.google.android.gms.internal.zzad;

public class zzbb extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzby.toString();
    }
    
    public zzbb() {
        super(zzbb.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final Locale default1 = Locale.getDefault();
        if (default1 == null) {
            return zzdf.zzBg();
        }
        final String language = default1.getLanguage();
        if (language == null) {
            return zzdf.zzBg();
        }
        return zzdf.zzK(language.toLowerCase());
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
