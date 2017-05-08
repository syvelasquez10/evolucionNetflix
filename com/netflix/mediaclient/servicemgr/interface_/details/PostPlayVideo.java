// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.List;

public interface PostPlayVideo extends VideoDetails
{
    List<String> getEpisodeBadges();
    
    String getInterestingSmallUrl();
    
    String getInterestingUrl();
    
    String getNarrative();
}
