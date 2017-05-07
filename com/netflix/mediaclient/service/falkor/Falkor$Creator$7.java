// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.LoLoMoSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.Func;

final class Falkor$Creator$7 implements Func<SummarizedList<Ref, LoLoMoSummary>>
{
    @Override
    public SummarizedList<Ref, LoLoMoSummary> call() {
        return new SummarizedList<Ref, LoLoMoSummary>(Falkor$Creator.Ref, Falkor$Creator.LoLoMoSummary);
    }
}
