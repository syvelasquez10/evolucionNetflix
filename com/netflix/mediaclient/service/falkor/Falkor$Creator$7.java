// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.Func;

final class Falkor$Creator$7 implements Func<SearchTrackableListSummary>
{
    @Override
    public SearchTrackableListSummary call() {
        return new SearchTrackableListSummary();
    }
}
