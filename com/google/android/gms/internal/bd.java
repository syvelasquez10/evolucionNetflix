// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface bd extends IInterface
{
    d X();
    
    ay Y();
    
    void a(final ay p0);
    
    void a(final bc p0);
    
    void a(final bf p0);
    
    void a(final eh p0);
    
    void a(final el p0, final String p1);
    
    void a(final et p0);
    
    void a(final eu p0);
    
    boolean a(final av p0);
    
    void aj();
    
    void destroy();
    
    String getMediationAdapterClassName();
    
    boolean isReady();
    
    void pause();
    
    void resume();
    
    void showInterstitial();
    
    void stopLoading();
}
