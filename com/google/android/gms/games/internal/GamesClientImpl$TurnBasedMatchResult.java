// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.g;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.a;

abstract class GamesClientImpl$TurnBasedMatchResult extends a
{
    final TurnBasedMatch WY;
    
    GamesClientImpl$TurnBasedMatchResult(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder = (DataHolder)new TurnBasedMatchBuffer(dataHolder);
        try {
            if (((g)dataHolder).getCount() > 0) {
                this.WY = ((g<TurnBasedMatch>)dataHolder).get(0).freeze();
            }
            else {
                this.WY = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    public TurnBasedMatch getMatch() {
        return this.WY;
    }
}
