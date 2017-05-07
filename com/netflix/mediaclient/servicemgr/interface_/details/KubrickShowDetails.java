// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;

public interface KubrickShowDetails extends KubrickVideo, ShowDetails
{
    List<String> getHeroImages();
}
