// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataBuffer;

public final class GameBadgeBuffer extends DataBuffer<GameBadge>
{
    public GameBadge bf(final int n) {
        return new GameBadgeRef(this.BB, n);
    }
}
