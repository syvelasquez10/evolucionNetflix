// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.Func;

final class Falkor$Creator$13 implements Func<FalkorVideo>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$13(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorVideo call() {
        return new FalkorVideo(this.val$proxy);
    }
}