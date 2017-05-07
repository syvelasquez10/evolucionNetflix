// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Comparator;

class ap$1 implements Comparator<String>
{
    final /* synthetic */ ap nO;
    
    ap$1(final ap no) {
        this.nO = no;
    }
    
    @Override
    public int compare(final String s, final String s2) {
        return s2.length() - s.length();
    }
}
