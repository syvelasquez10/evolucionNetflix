// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface ContainerHolder extends Releasable, Result
{
    Container getContainer();
    
    void refresh();
    
    void setContainerAvailableListener(final ContainerAvailableListener p0);
    
    public interface ContainerAvailableListener
    {
        void onContainerAvailable(final ContainerHolder p0, final String p1);
    }
}
