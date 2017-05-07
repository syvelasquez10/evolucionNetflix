// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.android.app.Status;

public interface ManagerStatusListener
{
    void onManagerReady(final ServiceManager p0, final Status p1);
    
    void onManagerUnavailable(final ServiceManager p0, final Status p1);
}
