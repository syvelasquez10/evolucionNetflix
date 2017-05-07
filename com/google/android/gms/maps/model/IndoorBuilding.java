// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.Iterator;
import com.google.android.gms.maps.model.internal.e$a;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.d;

public final class IndoorBuilding
{
    private final d ajL;
    
    public IndoorBuilding(final d d) {
        this.ajL = n.i(d);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.ajL.b(((IndoorBuilding)o).ajL);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getActiveLevelIndex() {
        try {
            return this.ajL.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public int getDefaultLevelIndex() {
        try {
            return this.ajL.getActiveLevelIndex();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public List<IndoorLevel> getLevels() {
        ArrayList list;
        try {
            final List<IBinder> levels = this.ajL.getLevels();
            list = new ArrayList<IndoorLevel>(levels.size());
            final Iterator<IBinder> iterator = levels.iterator();
            while (iterator.hasNext()) {
                list.add(new IndoorLevel(e$a.bt(iterator.next())));
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
            return this.ajL.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public boolean isUnderground() {
        try {
            return this.ajL.isUnderground();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
