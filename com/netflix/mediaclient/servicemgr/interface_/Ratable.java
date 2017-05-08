// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Ratable
{
    int getMatchPercentage();
    
    float getPredictedRating();
    
    float getUserRating();
    
    int getUserThumbRating();
    
    boolean isNewForPvr();
    
    void setUserRating(final float p0);
    
    void setUserThumbRating(final int p0);
}
