// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.falkor.Func;

final class Falkor$Creator$20 implements Func<BillboardSummary>
{
    @Override
    public BillboardSummary call() {
        return new BillboardSummary();
    }
}
