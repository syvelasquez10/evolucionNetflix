// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface PostPlayVideo extends VideoDetails
{
    String getInterestingUrl();
    
    String getPostPlayRequestId();
    
    int getPostPlayTrackId();
}
