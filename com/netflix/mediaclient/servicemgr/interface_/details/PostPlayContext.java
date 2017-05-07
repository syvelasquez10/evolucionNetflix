// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.model.branches.FalkorObject;

public interface PostPlayContext extends FalkorObject
{
    String getRequestId();
    
    int getTrackId();
}
