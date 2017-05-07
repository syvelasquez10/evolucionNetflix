// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.Session;

public class v$a
{
    private Session Sk;
    
    public v$a b(final Session sk) {
        n.b(sk.getEndTimeMillis() == 0L, (Object)"Cannot start a session which has already ended");
        this.Sk = sk;
        return this;
    }
    
    public v jx() {
        return new v(this, null);
    }
}
