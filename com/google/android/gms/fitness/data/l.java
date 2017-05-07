// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.HashMap;
import java.util.Map;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.request.DataSourceListener;

public class l extends k.a
{
    private final DataSourceListener SY;
    
    private l(final DataSourceListener dataSourceListener) {
        this.SY = n.i(dataSourceListener);
    }
    
    public void onEvent(final DataPoint dataPoint) throws RemoteException {
        this.SY.onEvent(dataPoint);
    }
    
    public static class a
    {
        private static final a SZ;
        private final Map<DataSourceListener, l> Ta;
        
        static {
            SZ = new a();
        }
        
        private a() {
            this.Ta = new HashMap<DataSourceListener, l>();
        }
        
        public static a iO() {
            return a.SZ;
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
}
