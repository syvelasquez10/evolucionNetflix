// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorIrisNotification;
import com.netflix.falkor.Func;

final class Falkor$Creator$18 implements Func<FalkorIrisNotification>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$18(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public FalkorIrisNotification call() {
        return new FalkorIrisNotification(this.val$proxy);
    }
}
