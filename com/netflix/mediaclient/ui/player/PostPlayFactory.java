// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.android.activity.NetflixActivity;

public class PostPlayFactory
{
    static PostPlay create(final PlayerFragment playerFragment, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        return new PostPlayForPlayer(playerFragment);
    }
    
    static PostPlay createForMdx(final NetflixActivity netflixActivity) {
        return new PostPlayForMDX(netflixActivity);
    }
}
