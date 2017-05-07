// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Locale;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class bc extends aj
{
    private static final String ID;
    
    static {
        ID = a.L.toString();
    }
    
    public bc() {
        super(bc.ID, new String[0]);
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final Locale default1 = Locale.getDefault();
        if (default1 == null) {
            return dh.lT();
        }
        final String language = default1.getLanguage();
        if (language == null) {
            return dh.lT();
        }
        return dh.r(language.toLowerCase());
    }
}
