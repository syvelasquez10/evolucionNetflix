// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

public interface MovieDetails extends EvidenceDetails, Similarable, VideoDetails
{
    String getDirectors();
    
    int getNumDirectors();
}
