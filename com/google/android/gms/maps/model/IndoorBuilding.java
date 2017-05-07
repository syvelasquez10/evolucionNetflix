// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.Iterator;
import com.google.android.gms.maps.model.internal.e;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.internal.fq;
import com.google.android.gms.maps.model.internal.d;

public final class IndoorBuilding
{
    private final d SY;
    
    public IndoorBuilding(final d d) {
        this.SY = fq.f(d);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.SY.b(((IndoorBuilding)o).SY);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getActiveLevelIndex() {
        try {
            return this.SY.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getDefaultLevelIndex() {
        try {
            return this.SY.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<IndoorLevel> getLevels() {
        ArrayList list;
        try {
            final List<IBinder> levels = this.SY.getLevels();
            list = new ArrayList<IndoorLevel>(levels.size());
            final Iterator<IBinder> iterator = levels.iterator();
            while (iterator.hasNext()) {
                list.add(new IndoorLevel(e.a.aF(iterator.next())));
            }
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
        return (List<IndoorLevel>)list;
    }
    
    @Override
    public int hashCode() {
        try {
            return this.SY.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isUnderground() {
        try {
            return this.SY.isUnderground();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
