// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;

class Container$b implements s$a
{
    final /* synthetic */ Container anY;
    
    private Container$b(final Container anY) {
        this.anY = anY;
    }
    
    @Override
    public Object b(final String s, final Map<String, Object> map) {
        final Container$FunctionCallTagCallback cl = this.anY.cl(s);
        if (cl != null) {
            cl.execute(s, map);
        }
        return di.pH();
    }
}
