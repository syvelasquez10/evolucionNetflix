// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadOwnerCoverPhotoUrisResult;

class PlayersImpl$LoadOwnerCoverPhotoUrisImpl$1 implements Players$LoadOwnerCoverPhotoUrisResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ PlayersImpl$LoadOwnerCoverPhotoUrisImpl Za;
    
    PlayersImpl$LoadOwnerCoverPhotoUrisImpl$1(final PlayersImpl$LoadOwnerCoverPhotoUrisImpl za, final Status cw) {
        this.Za = za;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
