// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import android.os.IInterface;

public interface io extends IInterface
{
    void a(final ApplicationMetadata p0, final String p1, final String p2, final boolean p3);
    
    void a(final String p0, final double p1, final boolean p2);
    
    void a(final String p0, final long p1);
    
    void a(final String p0, final long p1, final int p2);
    
    void ac(final int p0);
    
    void ad(final int p0);
    
    void ae(final int p0);
    
    void af(final int p0);
    
    void b(final ig p0);
    
    void b(final il p0);
    
    void b(final String p0, final byte[] p1);
    
    void k(final String p0, final String p1);
    
    void onApplicationDisconnected(final int p0);
}
