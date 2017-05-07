// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.client;

import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.LoMo;

public class SocialGroupPlaceholder extends SocialPlaceholder
{
    public SocialGroupPlaceholder(final LoMo loMo) {
        super(loMo);
    }
    
    @Override
    public VideoType getType() {
        return VideoType.SOCIAL_GROUP;
    }
}
