// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadExtendedGamesResultImpl extends a implements GamesMetadata$LoadExtendedGamesResult
{
    private final ExtendedGameBuffer WI;
    
    GamesClientImpl$LoadExtendedGamesResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WI = new ExtendedGameBuffer(dataHolder);
    }
}
