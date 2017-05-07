// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.IInterface;

public interface zzn extends IInterface
{
    void onAdClosed();
    
    void onAdFailedToLoad(final int p0);
    
    void onAdLeftApplication();
    
    void onAdLoaded();
    
    void onAdOpened();
}
