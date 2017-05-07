// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.HashMap;
import com.google.android.gms.fitness.request.DataSourceListener;
import java.util.Map;

public class l$a
{
    private static final l$a SZ;
    private final Map<DataSourceListener, l> Ta;
    
    static {
        SZ = new l$a();
    }
    
    private l$a() {
        this.Ta = new HashMap<DataSourceListener, l>();
    }
    
    public static l$a iO() {
        return l$a.SZ;
    }
    
    public l a(final DataSourceListener dataSourceListener) {
        synchronized (this.Ta) {
            l l;
            if ((l = this.Ta.get(dataSourceListener)) == null) {
                l = new l(dataSourceListener, null);
                this.Ta.put(dataSourceListener, l);
            }
            return l;
        }
    }
    
    public l b(final DataSourceListener dataSourceListener) {
        synchronized (this.Ta) {
            return this.Ta.get(dataSourceListener);
        }
    }
    
    public l c(final DataSourceListener dataSourceListener) {
        synchronized (this.Ta) {
            final l l = this.Ta.remove(dataSourceListener);
            if (l != null) {
                return l;
            }
            return new l(dataSourceListener, null);
        }
    }
}
