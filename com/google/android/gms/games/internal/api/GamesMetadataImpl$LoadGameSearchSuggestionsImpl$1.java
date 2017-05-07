// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGameSearchSuggestionsResult;

class GamesMetadataImpl$LoadGameSearchSuggestionsImpl$1 implements GamesMetadata$LoadGameSearchSuggestionsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ GamesMetadataImpl$LoadGameSearchSuggestionsImpl Ys;
    
    GamesMetadataImpl$LoadGameSearchSuggestionsImpl$1(final GamesMetadataImpl$LoadGameSearchSuggestionsImpl ys, final Status cw) {
        this.Ys = ys;
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
