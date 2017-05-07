// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.content.Intent;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface IGamesSignInCallbacks extends IInterface
{
    void T(final DataHolder p0);
    
    void U(final DataHolder p0);
    
    void b(final int p0, final Intent p1);
    
    void dD(final int p0);
    
    void dE(final int p0);
    
    void g(final DataHolder p0);
}
