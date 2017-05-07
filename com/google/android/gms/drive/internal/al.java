// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;

public class al extends c
{
    private final a.d<Status> wH;
    
    public al(final a.d<Status> wh) {
        this.wH = wh;
    }
    
    @Override
    public void m(final Status status) throws RemoteException {
        this.wH.b(status);
    }
    
    @Override
    public void onSuccess() throws RemoteException {
        this.wH.b(Status.Bv);
    }
}
