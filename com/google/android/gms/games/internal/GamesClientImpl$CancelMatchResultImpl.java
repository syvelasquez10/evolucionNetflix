// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;

final class GamesClientImpl$CancelMatchResultImpl implements TurnBasedMultiplayer$CancelMatchResult
{
    private final Status CM;
    private final String Wu;
    
    GamesClientImpl$CancelMatchResultImpl(final Status cm, final String wu) {
        this.CM = cm;
        this.Wu = wu;
    }
    
    @Override
    public String getMatchId() {
        return this.Wu;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
