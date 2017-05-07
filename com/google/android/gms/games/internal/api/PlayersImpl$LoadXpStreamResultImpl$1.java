// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadXpStreamResult;

class PlayersImpl$LoadXpStreamResultImpl$1 implements Players$LoadXpStreamResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ PlayersImpl$LoadXpStreamResultImpl Ze;
    
    PlayersImpl$LoadXpStreamResultImpl$1(final PlayersImpl$LoadXpStreamResultImpl ze, final Status cw) {
        this.Ze = ze;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
