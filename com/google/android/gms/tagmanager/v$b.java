// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Arrays;

class v$b
{
    final String JH;
    final byte[] aoO;
    
    v$b(final String jh, final byte[] aoO) {
        this.JH = jh;
        this.aoO = aoO;
    }
    
    @Override
    public String toString() {
        return "KeyAndSerialized: key = " + this.JH + " serialized hash = " + Arrays.hashCode(this.aoO);
    }
}
