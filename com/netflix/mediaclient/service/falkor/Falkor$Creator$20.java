// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorKidsCharacter;
import com.netflix.falkor.Func;

final class Falkor$Creator$20 implements Func<FalkorKidsCharacter>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$20(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorKidsCharacter call() {
        return new FalkorKidsCharacter(this.val$proxy);
    }
}
