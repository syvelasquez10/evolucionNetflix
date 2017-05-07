// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorSuggestion;
import com.netflix.falkor.Func;

final class Falkor$Creator$16 implements Func<FalkorSuggestion>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$16(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorSuggestion call() {
        return new FalkorSuggestion(this.val$proxy);
    }
}
