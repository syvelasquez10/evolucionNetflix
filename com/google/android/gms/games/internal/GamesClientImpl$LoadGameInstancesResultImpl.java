// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.GamesMetadata$LoadGameInstancesResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadGameInstancesResultImpl extends a implements GamesMetadata$LoadGameInstancesResult
{
    private final GameInstanceBuffer WJ;
    
    GamesClientImpl$LoadGameInstancesResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WJ = new GameInstanceBuffer(dataHolder);
    }
}
