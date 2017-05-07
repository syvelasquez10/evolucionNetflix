// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.android.activity.NetflixActivity;

public class PostPlayFactory
{
    static PostPlay create(final PlayerFragment playerFragment, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        switch (PostPlayFactory$1.$SwitchMap$com$netflix$mediaclient$ui$player$PostPlayFactory$PostPlayType[postPlayFactory$PostPlayType.ordinal()]) {
            default: {
                return new PostPlayForMovies(playerFragment);
            }
            case 1:
            case 2: {
                return new PostPlayForEpisodes(playerFragment);
            }
        }
    }
    
    static PostPlay createForMdx(final NetflixActivity netflixActivity) {
        return new PostPlayForMDX(netflixActivity);
    }
}
