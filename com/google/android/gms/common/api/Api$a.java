// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Looper;

public interface Api$a
{
    void connect();
    
    void disconnect();
    
    Looper getLooper();
    
    boolean isConnected();
}
