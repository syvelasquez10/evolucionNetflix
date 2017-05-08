// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface RatingInfo
{
    int getMatchPercentage();
    
    float getUserRating();
    
    int getUserThumbRating();
    
    boolean isNewForPvr();
}
