// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.servicemgr.model.UserRating;

public class Video$UserRating implements UserRating
{
    public float userRating;
    
    @Override
    public float getUserRating() {
        return this.userRating;
    }
    
    @Override
    public String toString() {
        return "Rating [userRating=" + this.userRating + "]";
    }
}
