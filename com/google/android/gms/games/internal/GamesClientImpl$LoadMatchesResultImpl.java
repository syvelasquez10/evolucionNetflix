// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;

final class GamesClientImpl$LoadMatchesResultImpl implements TurnBasedMultiplayer$LoadMatchesResult
{
    private final Status CM;
    private final LoadMatchesResponse WM;
    
    GamesClientImpl$LoadMatchesResultImpl(final Status cm, final Bundle bundle) {
        this.CM = cm;
        this.WM = new LoadMatchesResponse(bundle);
    }
    
    @Override
    public LoadMatchesResponse getMatches() {
        return this.WM;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    @Override
    public void release() {
        this.WM.close();
    }
}
