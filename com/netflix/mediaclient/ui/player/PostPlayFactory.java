// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class PostPlayFactory
{
    static PostPlay create(final PlayerActivity playerActivity, final PostPlayFactory$PostPlayType postPlayFactory$PostPlayType) {
        switch (PostPlayFactory$1.$SwitchMap$com$netflix$mediaclient$ui$player$PostPlayFactory$PostPlayType[postPlayFactory$PostPlayType.ordinal()]) {
            default: {
                return new PostPlayForMovies(playerActivity);
            }
            case 1: {
                return new PostPlayForMDX(playerActivity);
            }
            case 2:
            case 3: {
                return new PostPlayForEpisodes(playerActivity);
            }
        }
    }
}
