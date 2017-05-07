// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import java.util.Set;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$UpdateRequestsResultImpl extends a implements Requests$UpdateRequestsResult
{
    private final RequestUpdateOutcomes XJ;
    
    GamesClientImpl$UpdateRequestsResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.XJ = RequestUpdateOutcomes.V(dataHolder);
    }
    
    @Override
    public Set<String> getRequestIds() {
        return this.XJ.getRequestIds();
    }
    
    @Override
    public int getRequestOutcome(final String s) {
        return this.XJ.getRequestOutcome(s);
    }
}
