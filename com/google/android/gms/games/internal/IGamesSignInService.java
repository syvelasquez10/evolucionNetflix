// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.IInterface;

public interface IGamesSignInService extends IInterface
{
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2);
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String p3);
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String p3, final String[] p4);
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String[] p3);
    
    void a(final IGamesSignInCallbacks p0, final String p1, final String p2, final String[] p3, final String p4);
    
    void b(final IGamesSignInCallbacks p0, final String p1, final String p2, final String p3);
    
    String bI(final String p0);
    
    String bJ(final String p0);
    
    String h(final String p0, final boolean p1);
    
    void w(final String p0, final String p1);
}
