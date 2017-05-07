// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGamesResult;

class GamesMetadataImpl$LoadGamesImpl$1 implements GamesMetadata$LoadGamesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ GamesMetadataImpl$LoadGamesImpl Yt;
    
    GamesMetadataImpl$LoadGamesImpl$1(final GamesMetadataImpl$LoadGamesImpl yt, final Status cw) {
        this.Yt = yt;
        this.CW = cw;
    }
    
    @Override
    public GameBuffer getGames() {
        return new GameBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
