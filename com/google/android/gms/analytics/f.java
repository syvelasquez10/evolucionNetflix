// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

interface f
{
    void dI();
    
    void dO();
    
    LinkedBlockingQueue<Runnable> dP();
    
    void dispatch();
    
    Thread getThread();
    
    void u(final Map<String, String> p0);
}
