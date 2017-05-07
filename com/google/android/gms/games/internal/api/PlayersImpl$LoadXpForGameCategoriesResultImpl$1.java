// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadXpForGameCategoriesResult;

class PlayersImpl$LoadXpForGameCategoriesResultImpl$1 implements Players$LoadXpForGameCategoriesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ PlayersImpl$LoadXpForGameCategoriesResultImpl Zd;
    
    PlayersImpl$LoadXpForGameCategoriesResultImpl$1(final PlayersImpl$LoadXpForGameCategoriesResultImpl zd, final Status cw) {
        this.Zd = zd;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
