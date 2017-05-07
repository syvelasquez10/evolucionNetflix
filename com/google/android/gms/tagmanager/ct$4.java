// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import java.util.Set;

class ct$4 implements ct$a
{
    final /* synthetic */ ct aqT;
    
    ct$4(final ct aqT) {
        this.aqT = aqT;
    }
    
    @Override
    public void a(final cr$e cr$e, final Set<cr$a> set, final Set<cr$a> set2, final cn cn) {
        set.addAll(cr$e.pc());
        set2.addAll(cr$e.pd());
        cn.oA().c(cr$e.pc(), cr$e.ph());
        cn.oB().c(cr$e.pd(), cr$e.pi());
    }
}
