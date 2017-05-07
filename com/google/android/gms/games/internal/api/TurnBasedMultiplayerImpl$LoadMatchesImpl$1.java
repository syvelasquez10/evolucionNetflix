// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;

class TurnBasedMultiplayerImpl$LoadMatchesImpl$1 implements TurnBasedMultiplayer$LoadMatchesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$LoadMatchesImpl ZZ;
    
    TurnBasedMultiplayerImpl$LoadMatchesImpl$1(final TurnBasedMultiplayerImpl$LoadMatchesImpl zz, final Status cw) {
        this.ZZ = zz;
        this.CW = cw;
    }
    
    @Override
    public LoadMatchesResponse getMatches() {
        return new LoadMatchesResponse(new Bundle());
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
