// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Map;

class ct$3 implements ct$a
{
    final /* synthetic */ ct aqT;
    final /* synthetic */ Map aqU;
    final /* synthetic */ Map aqV;
    final /* synthetic */ Map aqW;
    final /* synthetic */ Map aqX;
    
    ct$3(final ct aqT, final Map aqU, final Map aqV, final Map aqW, final Map aqX) {
        this.aqT = aqT;
        this.aqU = aqU;
        this.aqV = aqV;
        this.aqW = aqW;
        this.aqX = aqX;
    }
    
    @Override
    public void a(final cr$e cr$e, final Set<cr$a> set, final Set<cr$a> set2, final cn cn) {
        final List<? extends cr$a> list = this.aqU.get(cr$e);
        final List<String> list2 = this.aqV.get(cr$e);
        if (list != null) {
            set.addAll(list);
            cn.oy().c((List<cr$a>)list, list2);
        }
        final List<? extends cr$a> list3 = this.aqW.get(cr$e);
        final List<String> list4 = this.aqX.get(cr$e);
        if (list3 != null) {
            set2.addAll(list3);
            cn.oz().c((List<cr$a>)list3, list4);
        }
    }
}
