// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadProfileSettingsResult;

class PlayersImpl$LoadProfileSettingsResultImpl$1 implements Players$LoadProfileSettingsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ PlayersImpl$LoadProfileSettingsResultImpl Zc;
    
    PlayersImpl$LoadProfileSettingsResultImpl$1(final PlayersImpl$LoadProfileSettingsResultImpl zc, final Status cw) {
        this.Zc = zc;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public boolean isProfileVisible() {
        return true;
    }
    
    @Override
    public boolean isVisibilityExplicitlySet() {
        return false;
    }
}
