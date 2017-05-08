// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.falkor.Func;

final class Falkor$Creator$17 implements Func<FalkorActorStill>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$17(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorActorStill call() {
        return new FalkorActorStill(this.val$proxy);
    }
}
