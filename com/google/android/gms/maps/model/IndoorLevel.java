// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.model.internal.e;

public final class IndoorLevel
{
    private final e ajM;
    
    public IndoorLevel(final e e) {
        this.ajM = n.i(e);
    }
    
    public void activate() {
        try {
            this.ajM.activate();
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
            return this.ajM.a(((IndoorLevel)o).ajM);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getName() {
        try {
            return this.ajM.getName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public String getShortName() {
        try {
            return this.ajM.getShortName();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Override
    public int hashCode() {
        try {
            return this.ajM.hashCodeRemote();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
