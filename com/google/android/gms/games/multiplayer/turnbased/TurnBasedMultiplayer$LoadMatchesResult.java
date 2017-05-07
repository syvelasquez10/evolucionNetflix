// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface TurnBasedMultiplayer$LoadMatchesResult extends Releasable, Result
{
    LoadMatchesResponse getMatches();
}
