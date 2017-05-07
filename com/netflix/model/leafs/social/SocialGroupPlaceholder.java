// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.LoMo;

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
