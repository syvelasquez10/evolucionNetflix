// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface Ratable
{
    float getPredictedRating();
    
    float getUserRating();
    
    void setUserRating(final float p0);
}
