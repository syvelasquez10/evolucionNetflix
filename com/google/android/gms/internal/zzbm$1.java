// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Comparator;

class zzbm$1 implements Comparator<String>
{
    final /* synthetic */ zzbm zzst;
    
    zzbm$1(final zzbm zzst) {
        this.zzst = zzst;
    }
    
    @Override
    public int compare(final String s, final String s2) {
        return s2.length() - s.length();
    }
}
