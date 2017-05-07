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
        ID = a.N.toString();
    }
    
    public bc() {
        super(bc.ID, new String[0]);
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final Locale default1 = Locale.getDefault();
        if (default1 == null) {
            return di.pI();
        }
        final String language = default1.getLanguage();
        if (language == null) {
            return di.pI();
        }
        return di.u(language.toLowerCase());
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
