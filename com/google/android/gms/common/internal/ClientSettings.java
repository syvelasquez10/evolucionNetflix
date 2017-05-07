// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.List;
import java.util.Collection;
import android.view.View;

public final class ClientSettings
{
    private final View IG;
    private final ClientSettings$ParcelableClientSettings Lk;
    
    public ClientSettings(final String s, final Collection<String> collection, final int n, final View ig, final String s2) {
        this.Lk = new ClientSettings$ParcelableClientSettings(s, collection, n, s2);
        this.IG = ig;
    }
    
    public List<String> getScopes() {
        return this.Lk.getScopes();
    }
}
