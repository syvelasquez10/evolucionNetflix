// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.client;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public class SocialConnectPlaceholder extends SocialPlaceholder
{
    public SocialConnectPlaceholder(final LoMo loMo) {
        super(loMo);
    }
    
    @Override
    public Playable getPlayable() {
        return null;
    }
    
    @Override
    public VideoType getType() {
        return VideoType.SOCIAL_POPULAR;
    }
}
