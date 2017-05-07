// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface cu extends IInterface
{
    void a(final d p0, final av p1, final String p2, final cv p3);
    
    void a(final d p0, final av p1, final String p2, final String p3, final cv p4);
    
    void a(final d p0, final ay p1, final av p2, final String p3, final cv p4);
    
    void a(final d p0, final ay p1, final av p2, final String p3, final String p4, final cv p5);
    
    void destroy();
    
    d getView();
    
    void pause();
    
    void resume();
    
    void showInterstitial();
}
