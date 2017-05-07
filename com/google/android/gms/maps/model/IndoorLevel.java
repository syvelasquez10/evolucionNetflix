// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.fq;
import com.google.android.gms.maps.model.internal.e;

public final class IndoorLevel
{
    private final e SZ;
    
    public IndoorLevel(final e e) {
        this.SZ = fq.f(e);
    }
    
    public void activate() {
        try {
            this.SZ.activate();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IndoorLevel)) {
            return false;
        }
        try {
            return this.SZ.a(((IndoorLevel)o).SZ);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getName() {
        try {
            return this.SZ.getName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getShortName() {
        try {
            return this.SZ.getShortName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.SZ.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
