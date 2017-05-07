// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.branches.FalkorSocialBadge;
import com.netflix.falkor.Func;

final class Falkor$Creator$5 implements Func<FalkorSocialBadge>
{
    @Override
    public FalkorSocialBadge call() {
        return new FalkorSocialBadge(Falkor$Creator.SocialBadge);
    }
}
