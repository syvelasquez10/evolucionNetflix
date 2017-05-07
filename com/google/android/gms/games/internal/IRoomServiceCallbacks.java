// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.IInterface;

public interface IRoomServiceCallbacks extends IInterface
{
    void a(final ParcelFileDescriptor p0, final int p1);
    
    void a(final ConnectionInfo p0);
    
    void a(final String p0, final byte[] p1, final int p2);
    
    void a(final String p0, final String[] p1);
    
    void aD(final IBinder p0);
    
    void b(final String p0, final String[] p1);
    
    void bM(final String p0);
    
    void bN(final String p0);
    
    void bO(final String p0);
    
    void bP(final String p0);
    
    void bQ(final String p0);
    
    void bR(final String p0);
    
    void c(final int p0, final int p1, final String p2);
    
    void c(final String p0, final byte[] p1);
    
    void c(final String p0, final String[] p1);
    
    void d(final String p0, final String[] p1);
    
    void dF(final int p0);
    
    void e(final String p0, final String[] p1);
    
    void f(final String p0, final String[] p1);
    
    void g(final String p0, final String[] p1);
    
    void i(final String p0, final boolean p1);
    
    void kH();
    
    void kI();
    
    void onP2PConnected(final String p0);
    
    void onP2PDisconnected(final String p0);
    
    void v(final String p0, final int p1);
}
