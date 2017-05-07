// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.Subscription;

public class ae$a
{
    private Subscription UH;
    private boolean UI;
    
    public ae$a() {
        this.UI = false;
    }
    
    public ae$a b(final Subscription uh) {
        this.UH = uh;
        return this;
    }
    
    public ae jD() {
        n.a(this.UH != null, (Object)"Must call setSubscription()");
        return new ae(this, null);
    }
}
