// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Players$LoadPlayersResult extends Releasable, Result
{
    PlayerBuffer getPlayers();
}
