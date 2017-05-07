// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;

public interface eg extends IInterface
{
    String getProductId();
    
    void recordPlayBillingResolution(final int p0);
    
    void recordResolution(final int p0);
}
