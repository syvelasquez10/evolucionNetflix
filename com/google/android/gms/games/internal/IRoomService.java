// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.IBinder;
import android.os.IInterface;

public interface IRoomService extends IInterface
{
    void Q(final boolean p0);
    
    void a(final IBinder p0, final IRoomServiceCallbacks p1);
    
    void a(final DataHolder p0, final boolean p1);
    
    void a(final byte[] p0, final String p1, final int p2);
    
    void a(final byte[] p0, final String[] p1);
    
    void bK(final String p0);
    
    void bL(final String p0);
    
    void c(final String p0, final String p1, final String p2);
    
    void kD();
    
    void kE();
    
    void kF();
    
    void kG();
    
    void t(final String p0, final int p1);
    
    void u(final String p0, final int p1);
}
