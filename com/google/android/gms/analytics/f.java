// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

interface f
{
    void bR();
    
    void bW();
    
    void bY();
    
    LinkedBlockingQueue<Runnable> bZ();
    
    Thread getThread();
    
    void q(final Map<String, String> p0);
}
