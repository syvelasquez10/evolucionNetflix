// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public class z extends a
{
    private final com.google.android.gms.common.api.a.c<Status> jW;
    
    public z(final com.google.android.gms.common.api.a.c<Status> jw) {
        this.jW = jw;
    }
    
    @Override
    public void m(final Status status) throws RemoteException {
        this.jW.a(status);
    }
    
    @Override
    public void onSuccess() throws RemoteException {
        this.jW.a(Status.nA);
    }
}
