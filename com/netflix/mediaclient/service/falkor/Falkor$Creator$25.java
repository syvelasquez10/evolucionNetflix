// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorScene;
import com.netflix.falkor.Func;

final class Falkor$Creator$25 implements Func<FalkorScene>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$25(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorScene call() {
        return new FalkorScene(this.val$proxy);
    }
}
