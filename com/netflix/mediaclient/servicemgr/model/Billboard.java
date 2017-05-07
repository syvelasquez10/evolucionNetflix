// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.servicemgr.model.user.SocialBadge;

public interface Billboard extends Playable, Video
{
    String getCertification();
    
    int getNumOfSeasons();
    
    SocialBadge getSocialBadge();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    int getYear();
}
