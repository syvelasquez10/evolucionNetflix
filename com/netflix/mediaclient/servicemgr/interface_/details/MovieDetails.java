// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.Set;

public interface MovieDetails extends EvidenceDetails, Similarable, Trailerable, VideoDetails
{
    Set<String> getCharacterRoles();
    
    String getDirectors();
    
    int getNumDirectors();
}
