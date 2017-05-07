// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import java.util.List;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;

public interface KubrickShowDetails extends KubrickVideo, ShowDetails
{
    List<String> getHeroImages();
}
