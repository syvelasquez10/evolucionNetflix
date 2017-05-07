// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.IInterface;

public interface zzft extends IInterface
{
    void onActivityResult(final int p0, final int p1, final Intent p2);
    
    void onCreate();
    
    void onDestroy();
}
