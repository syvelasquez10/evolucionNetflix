// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.DiscoverySummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.Func;

final class Falkor$Creator$26 implements Func<SummarizedList<Ref, DiscoverySummary>>
{
    final /* synthetic */ ModelProxy val$proxy;
    
    Falkor$Creator$26(final ModelProxy val$proxy) {
        this.val$proxy = val$proxy;
    }
    
    @Override
    public SummarizedList<Ref, DiscoverySummary> call() {
        return new SummarizedList<Ref, DiscoverySummary>(Falkor$Creator.Ref, Falkor$Creator.FalkorDiscoverySummary(this.val$proxy));
    }
}
