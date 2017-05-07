// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;

class Container$a implements s$a
{
    final /* synthetic */ Container anY;
    
    private Container$a(final Container anY) {
        this.anY = anY;
    }
    
    @Override
    public Object b(final String s, final Map<String, Object> map) {
        final Container$FunctionCallMacroCallback ck = this.anY.ck(s);
        if (ck == null) {
            return null;
        }
        return ck.getValue(s, map);
    }
}
