// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Ratable
{
    float getPredictedRating();
    
    float getUserRating();
    
    void setUserRating(final float p0);
}
