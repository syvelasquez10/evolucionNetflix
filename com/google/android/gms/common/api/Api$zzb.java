// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.IBinder;
import android.os.IInterface;

public interface Api$zzb<T extends IInterface>
{
    String getServiceDescriptor();
    
    String getStartServiceAction();
    
    T zzT(final IBinder p0);
}
