// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.RemoteException;

public final class RuntimeRemoteException extends RuntimeException
{
    public RuntimeRemoteException(final RemoteException ex) {
        super((Throwable)ex);
    }
}
