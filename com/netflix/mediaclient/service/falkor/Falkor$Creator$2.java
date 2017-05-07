// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.falkor.Func;

final class Falkor$Creator$2 implements Func<TrackableListSummary>
{
    @Override
    public TrackableListSummary call() {
        return new TrackableListSummary();
    }
}
