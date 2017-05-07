// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;

class GamesMetadataImpl$LoadExtendedGamesImpl$1 implements GamesMetadata$LoadExtendedGamesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ GamesMetadataImpl$LoadExtendedGamesImpl Yq;
    
    GamesMetadataImpl$LoadExtendedGamesImpl$1(final GamesMetadataImpl$LoadExtendedGamesImpl yq, final Status cw) {
        this.Yq = yq;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
