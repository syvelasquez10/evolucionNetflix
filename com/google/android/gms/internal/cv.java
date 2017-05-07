// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;

public interface cv extends IInterface
{
    void onAdClicked();
    
    void onAdClosed();
    
    void onAdFailedToLoad(final int p0);
    
    void onAdLeftApplication();
    
    void onAdLoaded();
    
    void onAdOpened();
}
