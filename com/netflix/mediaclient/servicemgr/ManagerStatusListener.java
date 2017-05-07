// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface ManagerStatusListener
{
    void onManagerReady(final ServiceManager p0, final int p1);
    
    void onManagerUnavailable(final ServiceManager p0, final int p1);
}
