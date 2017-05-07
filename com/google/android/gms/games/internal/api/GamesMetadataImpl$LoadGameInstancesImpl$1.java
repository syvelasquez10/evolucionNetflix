// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGameInstancesResult;

class GamesMetadataImpl$LoadGameInstancesImpl$1 implements GamesMetadata$LoadGameInstancesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ GamesMetadataImpl$LoadGameInstancesImpl Yr;
    
    GamesMetadataImpl$LoadGameInstancesImpl$1(final GamesMetadataImpl$LoadGameInstancesImpl yr, final Status cw) {
        this.Yr = yr;
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
