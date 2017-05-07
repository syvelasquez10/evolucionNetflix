// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import java.util.Iterator;
import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.k;

class b implements SensorEventDispatcher
{
    private final k Up;
    
    b(final k k) {
        this.Up = n.i(k);
    }
    
    @Override
    public void publish(final DataPoint dataPoint) throws RemoteException {
        this.Up.onEvent(dataPoint);
    }
    
    @Override
    public void publish(final List<DataPoint> list) throws RemoteException {
        final Iterator<DataPoint> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.publish(iterator.next());
        }
    }
}
