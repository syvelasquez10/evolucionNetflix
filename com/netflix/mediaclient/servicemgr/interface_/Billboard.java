// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

import com.netflix.model.leafs.originals.BillboardSummary;

public interface Billboard extends Playable, Video
{
    BillboardSummary getBillboardSummary();
    
    String getCertification();
    
    String getHighResolutionLandscapeBoxArtUrl();
    
    int getNumOfSeasons();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    int getYear();
}
